package com.example.jpabatchrepository.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job userActivityLogJob;

    public BatchScheduler(JobLauncher jobLauncher, Job userActivityLogJob) {
        this.jobLauncher = jobLauncher;
        this.userActivityLogJob = userActivityLogJob;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void runUserActivityLogJob() throws Exception {
        jobLauncher.run(
                userActivityLogJob,
                new JobParametersBuilder()
                        .addString("runTime", LocalDateTime.now().toString())
                        .toJobParameters()
        );
    }
}
