package com.bachelors.dni.producers.newsapi.models;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsApiResponse {

    private String status;
    private Integer totalResults;
    private Set<NewsApiArticle> articles;

}
