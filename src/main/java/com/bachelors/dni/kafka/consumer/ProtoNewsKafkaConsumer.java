package com.bachelors.dni.kafka.consumer;

import com.bachelors.dni.protobuf.NewsArticleProto;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProtoNewsKafkaConsumer {

    @KafkaListener(topics = "zoo-b108c2e4-a3d2-4d8c-adcf-a09248abd76a")
    public void listen(byte[] article) throws InvalidProtocolBufferException {

        NewsArticleProto.Article protoArticle = NewsArticleProto.Article.parseFrom(article);
        System.out.println("I got this: " + protoArticle);

    }
}
