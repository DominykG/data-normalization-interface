package com.bachelors.nni.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bachelors.nni.business.jobs.KafkaProducerJob.job;

@RestController
public class ManualKafkaPublishController {

    @GetMapping("/publish")
    private String publishManually() {
        job();
        return "OK";
    }

}
