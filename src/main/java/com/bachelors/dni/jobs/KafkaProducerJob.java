package com.bachelors.dni.jobs;

import com.bachelors.dni.db.models.Client;
import com.bachelors.dni.db.models.Source;
import com.bachelors.dni.db.repositories.ClientRepository;
import com.bachelors.dni.kafka.producer.ProtoNewsKafkaProducer;
import com.bachelors.dni.producers.newsapi.models.NewsApiResponse;
import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.bachelors.dni.producers.newsapi.normalization.NewsApiNormalization.normalizeNewsApi;

@Log4j2
@Component
public class KafkaProducerJob {

    private static ProtoNewsKafkaProducer kafkaProducer;
    private static ClientRepository clientRepository;

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final Gson GSON = new Gson();

    private static final String API_KEY = "d7185b744f324f46a9df10b87e556ee6";

    private KafkaProducerJob(ProtoNewsKafkaProducer kafkaProducer,
                             ClientRepository clientRepository) {
        KafkaProducerJob.kafkaProducer = kafkaProducer;
        KafkaProducerJob.clientRepository = clientRepository;
    }

    public static void job() {
        getClients().forEach(
                (client) -> publishArticlesToKafka(
                        client.getAssignedKafkaTopic(),
                        requestArticlesFromNewsApi(client)
                )
        );
    }

    private static Set<Article> requestArticlesFromNewsApi(Client client) {
        String response = REST_TEMPLATE.getForObject(
                buildNewsApiRequest(client.getQuery(), client.getSources()), String.class);

        NewsApiResponse r = GSON.fromJson(response, NewsApiResponse.class);

        log.info(r);

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

        log.info(request);
        return request.toString();
    }


    private static List<Client> getClients() {
        return clientRepository.findAll();
    }

    private static void publishArticlesToKafka(String topic, Set<Article> articles) {
        articles.forEach( (article) -> kafkaProducer.send(topic, article));
    }

}
