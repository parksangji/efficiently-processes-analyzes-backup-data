package com.example.jpabatchrepository.config;

import com.example.jpabatchrepository.entity.UserActivityLog;
import com.example.jpabatchrepository.repository.UserActivityLogRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
public class UserActivityLogBatchConfig {

    private final JobRepository jobRepository;
    private final UserActivityLogRepository userActivityLogRepository;

    public UserActivityLogBatchConfig(JobRepository jobRepository, UserActivityLogRepository userActivityLogRepository) {
        this.jobRepository = jobRepository;
        this.userActivityLogRepository = userActivityLogRepository;
    }

    @Bean
    public RepositoryItemReader<UserActivityLog> userActivityLogReader() {
        return new RepositoryItemReaderBuilder<UserActivityLog>()
                .name("userActivityLogReader")
                .repository(userActivityLogRepository)
                .methodName("findAll") // 또는 특정 조건에 따른 조회 메소드
                .pageSize(100) // 한 번에 읽어올 데이터 크기
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemWriter<UserActivityLog> userActivityLogWriter() {
        return items -> {
            for (UserActivityLog item : items) {
                System.out.println("Processing: " + item.getActivityType() + " - " + item.getActivityTime());
                // 실제 데이터 처리 로직 (예: 다른 테이블에 저장, 분석 등)
            }
        };
    }

    @Bean
    public Step processUserActivityLogStep() {
        return new StepBuilder("processUserActivityLogStep", jobRepository)
                .<UserActivityLog, UserActivityLog>chunk(100)
                .reader(userActivityLogReader())
                .writer(userActivityLogWriter())
                .build();
    }

    @Bean
    public Job userActivityLogJob() {
        return new JobBuilder("userActivityLogJob", jobRepository)
                .start(processUserActivityLogStep())
                .build();
    }
}