package com.bachelors.nni.business.kafka.producer;

import com.bachelors.nni.protobuf.NewsArticleProto.Article;
import com.google.protobuf.Timestamp;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class ProtoNewsKafkaProducer {

    private final KafkaTemplate<String, byte[]> protoMessageKafkaTemplate;

    public ProtoNewsKafkaProducer(KafkaTemplate<String, byte[]> protoMessageKafkaTemplate) {
        this.protoMessageKafkaTemplate = protoMessageKafkaTemplate;
    }

    public void send(String kafkaTopic, byte[] article) {
        protoMessageKafkaTemplate.send(kafkaTopic, article);
    }
}
