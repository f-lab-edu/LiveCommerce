package com.flab.seller.infrastructure.config;

import com.flab.seller.domain.SessionRepository;
import com.flab.seller.infrastructure.persistence.SessionRepositoryAdaptor;
import com.flab.seller.infrastructure.persistence.redis.RedisSessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionRepositoryConfig {

    @Bean
    SessionRepository sessionIdRepository(RedisSessionRepository redisSessionRepository) {
        return new SessionRepositoryAdaptor(redisSessionRepository);
    }
}
