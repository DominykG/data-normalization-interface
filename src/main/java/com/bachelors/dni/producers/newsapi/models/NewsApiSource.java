package com.bachelors.dni.producers.newsapi.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsApiSource {

    private String id;
    private String name;

}
