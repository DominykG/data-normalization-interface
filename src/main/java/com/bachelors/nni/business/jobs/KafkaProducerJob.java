package com.bachelors.nni.business.jobs;

import com.bachelors.nni.business.kafka.producer.ProtoNewsKafkaProducer;
import com.bachelors.nni.business.producers.newsapi.models.NewsApiResponse;
import com.bachelors.nni.database.models.Client;
import com.bachelors.nni.database.models.Source;
import com.bachelors.nni.database.repositories.ClientRepository;
import com.bachelors.nni.protobuf.NewsArticleProto.Article;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.jobrunr.jobs.lambdas.JobLambda;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.bachelors.nni.business.producers.newsapi.normalization.NewsApiNormalization.normalizeNewsApi;
import static com.bachelors.nni.business.producers.rss.normalization.RssFeedNormalization.normalizeRssFeeds;

@Log4j2
@Component
public class KafkaProducerJob {

    private static ProtoNewsKafkaProducer kafkaProducer;
    private static ClientRepository clientRepository;

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final Gson GSON = new Gson();

    private static String API_KEY = "d7185b744f324f46a9df10b87e556ee6";

    private KafkaProducerJob(ProtoNewsKafkaProducer kafkaProducer,
                             ClientRepository clientRepository) {
        KafkaProducerJob.kafkaProducer = kafkaProducer;
        KafkaProducerJob.clientRepository = clientRepository;
    }

    public static void job() {
        log.info("Started job");
        getClients().forEach(
                (client) -> publishArticlesToKafka(
                        client.getAssignedKafkaTopic(),
                        requestArticles(client)
                )
        );
    }

    private static Set<Article> requestArticles(Client client) {

        Set<Article> articles = new HashSet<>(requestArticlesFromNewsApi(client));

        try {
            articles.addAll(normalizeRssFeeds(client));
        } catch (IOException e) {
            log.error("RSS ERROR");
        }

        return articles;
    }

    private static Set<Article> requestArticlesFromNewsApi(Client client) {
        String response = REST_TEMPLATE.getForObject(
                buildNewsApiRequest(client.getQuery(), client.getSources()), String.class);

        NewsApiResponse r = GSON.fromJson(response, NewsApiResponse.class);

        return normalizeNewsApi(r.getArticles());
    }

    private static String buildNewsApiRequest(String query, Set<Source> sources) {
        StringBuilder request = new StringBuilder("https://newsapi.org/v2/everything?");
        request.append("pageSize=100").append("&q=").append(query);

        if (!sources.isEmpty()) {
            request.append("&sources=");
            sources.forEach( (source) -> request.append(source.getId()).append(","));
        }

        request.deleteCharAt(request.length()-1)
                .append("&from=")
                .append(LocalDateTime.now().minusDays(1))
                .append("&apiKey=")
                .append(API_KEY);

        //log.info(request);
        return request.toString();
    }


    private static List<Client> getClients() {
        return clientRepository.findAll();
    }

    private static void publishArticlesToKafka(String topic, Set<Article> articles) {
        articles.forEach( (art) -> log.info(art.getContent()));
        articles.forEach( (article) -> kafkaProducer.send(topic, article.toByteArray()));
    }

}
