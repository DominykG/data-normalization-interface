package com.bachelors.dni.rest;

import com.bachelors.dni.kafka.producer.ProtoNewsKafkaProducer;
import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/dni/v1")
public class TestRestController {

    private static final String URL = "https://newsapi.org/v2/everything" +
            "?q=black AND lion&pageSize=100" +
            "&sortBy=publishedAt" +
            "&apiKey=d7185b744f324f46a9df10b87e556ee6";

    @Autowired
    ProtoNewsKafkaProducer producer;

    @GetMapping("/test")
    private ResponseEntity<Object> test(@RequestParam String topic) {

        Article article = Article.newBuilder()
                .setContent("Some nice content to this topic: " + topic)
                .build();

        producer.send(topic, article);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/test2")
    private ResponseEntity<Object> test2() {

        RestTemplate rt = new RestTemplate();

        String r = rt.getForObject(URL, String.class);

        StringBuilder response = new StringBuilder(r);

        response.delete(0, response.indexOf(": ["));


        return ResponseEntity.ok(response);
    }


}
