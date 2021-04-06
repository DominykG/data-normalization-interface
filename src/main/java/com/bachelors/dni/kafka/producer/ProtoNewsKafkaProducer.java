package com.bachelors.dni.kafka.producer;

import com.bachelors.dni.protobuf.NewsArticleProto.Article;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProtoNewsKafkaProducer {

    private final KafkaTemplate<String, byte[]> protoMessageKafkaTemplate;

    public ProtoNewsKafkaProducer(KafkaTemplate<String, byte[]> protoMessageKafkaTemplate) {
        this.protoMessageKafkaTemplate = protoMessageKafkaTemplate;
    }

    public void send(String kafkaTopic, Article article) {
        protoMessageKafkaTemplate.send(kafkaTopic, article.toByteArray());
    }
}
