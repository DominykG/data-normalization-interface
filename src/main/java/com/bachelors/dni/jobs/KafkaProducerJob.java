package com.bachelors.dni.jobs;


import com.bachelors.dni.kafka.producer.ProtoNewsKafkaProducer;
import com.bachelors.dni.producers.newsapi.models.NewsApiArticle;
import com.bachelors.dni.producers.newsapi.normalization.NewsApiNormalization;
import lombok.extern.log4j.Log4j2;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerJob {

    private JobScheduler jobScheduler;
    private ProtoNewsKafkaProducer producer;

    private KafkaProducerJob(JobScheduler jobScheduler, ProtoNewsKafkaProducer producer) {
        this.jobScheduler = jobScheduler;
        this.producer = producer;
    }

    public void scheduleKafkaJobForTopic(String topic) {

        jobScheduler.scheduleRecurrently(
                "news-api-daily-job",
                () -> {

                },
                Cron.every5minutes()
        );

    }


    private static List<NewsApiArticle> requestArticlesFromNewsApi() {



        return null;
    }
    
}
