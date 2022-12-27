package com.flab.seller.infrastructure.persistence.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.common.auth.AuthenticatedSeller;
import com.flab.seller.infrastructure.sessionproperties.SessionProperties;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisSessionRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final SessionProperties sessionProperties;

    public RedisSessionRepository(
            RedisTemplate<String, Object> redisTemplate,
            ObjectMapper objectMapper,
            SessionProperties sessionProperties
    ) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.sessionProperties = sessionProperties;
    }


    public void save(String sessionId, AuthenticatedSeller authenticatedSeller) {
        redisTemplate.opsForValue().set(sessionId, authenticatedSeller);
    }

    public void remove(String sessionId) {
        redisTemplate.delete(sessionId);
    }

    public AuthenticatedSeller findBySessionId(String sessionId) {
        var result = redisTemplate.opsForValue().get(sessionId);

        if (result == null) {
            return null;
        }

        return objectMapper.convertValue(result, AuthenticatedSeller.class);
    }

    public void renewExpirationSec(AuthenticatedSeller authenticatedSeller) {
        long expirationTime = calculateExpirationTime(authenticatedSeller);
        authenticatedSeller.addExpirationSec(expirationTime);

        redisTemplate.opsForValue().set(
                authenticatedSeller.getSessionId(),
                authenticatedSeller,
                Duration.ofSeconds(sessionProperties.getSessionExpirationSec())
        );
    }

    private long calculateExpirationTime(AuthenticatedSeller authenticatedSeller) {
        long sessionExpirationSec = sessionProperties.getSessionExpirationSec();
        long sellerExpirationSec = redisTemplate.getExpire(
                authenticatedSeller.getSessionId(),
                TimeUnit.SECONDS
        );

        if (sessionExpirationSec - sellerExpirationSec <= 0) {
            return sessionExpirationSec;
        }

        return sessionExpirationSec - sellerExpirationSec;
    }
}
