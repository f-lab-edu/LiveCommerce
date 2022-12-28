package com.flab.point.infrastructure.config;

import com.flab.point.domain.PointRepository;
import com.flab.point.infrastructure.persistence.PointRepositoryAdapter;
import com.flab.point.infrastructure.persistence.jpa.JpaPointRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointRepositoryConfig {

    @Bean
    public PointRepository pointRepository(JpaPointRepository jpaPointRepository) {
        return new PointRepositoryAdapter(jpaPointRepository);
    }
}
