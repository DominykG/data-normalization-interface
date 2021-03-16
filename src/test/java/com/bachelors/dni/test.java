package com.bachelors.dni;

import com.bachelors.dni.producers.newsapi.models.NewsApiResponse;
import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class test {

    private static final String URL = "https://newsapi.org/v2/everything" +
            "?q=black AND lion&pageSize=100" +
            "&sortBy=publishedAt" +
            "&apiKey=d7185b744f324f46a9df10b87e556ee6";


    @Test
    void test2() {

        Gson g = new Gson();

        RestTemplate rt = new RestTemplate();

        String response = rt.getForObject(URL, String.class);

        NewsApiResponse r = g.fromJson(response, NewsApiResponse.class);

        r.getArticles().forEach((newsApiArticle -> {
            log.info(newsApiArticle.getSource().getId());
            log.info(newsApiArticle.getSource().getName());
            log.info(newsApiArticle.getPublishedAt());
            }));

    }
}
