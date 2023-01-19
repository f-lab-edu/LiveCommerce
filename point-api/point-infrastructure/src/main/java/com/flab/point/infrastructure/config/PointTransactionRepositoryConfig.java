package com.flab.point.infrastructure.config;

import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.infrastructure.persistence.PointTransactionRepositoryAdapter;
import com.flab.point.infrastructure.persistence.jpa.JpaPointTransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointTransactionRepositoryConfig {

    @Bean
    public PointTransactionRepository pointTransactionRepository(JpaPointTransactionRepository jpaPointTransactionRepository) {
        return new PointTransactionRepositoryAdapter(jpaPointTransactionRepository);
    }
}
