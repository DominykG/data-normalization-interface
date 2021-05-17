package com.bachelors.nni;

import com.bachelors.nni.business.jobs.KafkaProducerJob;
import lombok.extern.log4j.Log4j2;
import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.sql.common.SqlStorageProviderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@Log4j2
@SpringBootApplication
public class NniApplication {

	public static void main(String[] args) {
		SpringApplication.run(NniApplication.class, args);
	}

	@Bean
	public JobScheduler initJobRunr(DataSource dataSource, JobActivator jobActivator) {
		return JobRunr.configure()
				.useStorageProvider(SqlStorageProviderFactory
						.using(dataSource))
				.useJobActivator(jobActivator)
				.useDefaultBackgroundJobServer()
				.useDashboard()
				.initialize();
	}

	@Bean
	public static void scheduleKafkaJobForTopic() {
		log.info("JobRunr init");
		BackgroundJob.scheduleRecurrently(
				"news-api-daily-job",
				Cron.daily(21, 34),
				KafkaProducerJob::job
		);
	}
}
