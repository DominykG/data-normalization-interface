package com.bachelors.dni.kafka.consumer;

import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProtoNewsKafkaConsumer {

    @KafkaListener(topics = "cryptoman-51820ece-3e78-4aa4-af72-957e16dfd835")
    public void listen(byte[] article) throws InvalidProtocolBufferException {

        Article protoArticle = Article.parseFrom(article);
        System.out.println("I got this: " + protoArticle);

    }
}
