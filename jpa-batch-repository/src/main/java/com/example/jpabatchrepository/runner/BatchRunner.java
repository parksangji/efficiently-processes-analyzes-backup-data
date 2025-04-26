package com.example.jpabatchrepository.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job userActivityLogJob;

    public BatchRunner(JobLauncher jobLauncher, Job userActivityLogJob) {
        this.jobLauncher = jobLauncher;
        this.userActivityLogJob = userActivityLogJob;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(
                userActivityLogJob,
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters()
        );
    }
}
