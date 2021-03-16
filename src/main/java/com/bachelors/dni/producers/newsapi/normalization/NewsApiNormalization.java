package com.bachelors.dni.producers.newsapi.normalization;

import com.bachelors.dni.producers.newsapi.models.NewsApiArticle;
import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import com.bachelors.dni.protobuf.NewsArticleProto.Source;
import com.google.protobuf.Timestamp;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class NewsApiNormalization {

    public static Article normalizeNewsApi(NewsApiArticle response) {
        return Article.newBuilder()
                .setSource(Source.newBuilder()
                        .setId(response.getSource().getId())
                        .setName(response.getSource().getName())
                        .build())
                .setAuthor(response.getAuthor())
                .setTitle(response.getTitle())
                .setDescription(response.getDescription())
                .setUrl(response.getUrl())
                .setImageUrl(response.getUrlToImage())
                .setDatePublish(stringToTimestamp(response.getPublishedAt()) )
                .setContent(response.getContent())
                .build();
    }

    private static Timestamp stringToTimestamp(String date) {
        return Timestamp.newBuilder()
                .setSeconds(LocalDateTime.parse(date).getSecond())
                .build();
    }

}
