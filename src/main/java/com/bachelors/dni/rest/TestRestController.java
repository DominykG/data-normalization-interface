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

@Log4j2
@RestController
@RequestMapping("/dni/v1")
public class TestRestController {

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


}
