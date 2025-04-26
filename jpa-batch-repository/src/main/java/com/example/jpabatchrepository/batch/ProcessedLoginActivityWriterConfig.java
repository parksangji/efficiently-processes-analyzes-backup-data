package com.example.jpabatchrepository.batch;

import com.example.jpabatchrepository.entity.ProcessedLoginActivity;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.persistence.EntityManagerFactory;

@Configuration
public class ProcessedLoginActivityWriterConfig {

    private final EntityManagerFactory entityManagerFactory;

    public ProcessedLoginActivityWriterConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public JpaItemWriter<ProcessedLoginActivity> processedLoginActivityWriter() {
        JpaItemWriter<ProcessedLoginActivity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }
}