package com.flab.user.infrastructure.config;

import com.flab.user.domain.TokenRepository;
import com.flab.user.infrastructure.TokenRepositoryAdapter;
import com.flab.user.infrastructure.persistence.redis.RedisTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenRepositoryConfig {

    @Bean
    public TokenRepository tokenRepository(RedisTokenRepository redisTokenRepository) {
        return new TokenRepositoryAdapter(redisTokenRepository);
    }
}
