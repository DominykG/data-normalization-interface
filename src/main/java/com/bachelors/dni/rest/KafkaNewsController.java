package com.bachelors.dni.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bachelors.dni.jobs.KafkaProducerJob.job;

@RestController
public class KafkaNewsController {

    @GetMapping("/publush")
    private String publish() {
        job();
        return "OK";
    }

}
