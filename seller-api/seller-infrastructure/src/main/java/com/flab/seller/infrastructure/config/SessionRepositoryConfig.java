package com.flab.seller.infrastructure.config;

import com.flab.seller.domain.SessionIdRepository;
import com.flab.seller.infrastructure.persistence.SessionIdRepositoryAdaptor;
import com.flab.seller.infrastructure.persistence.redis.RedisSessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionRepositoryConfig {

    @Bean
    SessionIdRepository sessionIdRepository(RedisSessionRepository redisSessionRepository) {
        return new SessionIdRepositoryAdaptor(redisSessionRepository);
    }
}
