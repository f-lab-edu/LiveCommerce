package com.flab.seller.infrastructure.persistence.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.common.auth.AuthenticatedSeller;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisSessionRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisSessionRepository(
            RedisTemplate<String, Object> redisTemplate,
            ObjectMapper objectMapper
    ) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }


    public void save(String sessionId, AuthenticatedSeller authenticatedSeller) {
        redisTemplate.opsForValue().set(sessionId, authenticatedSeller);
    }


}
