package com.flab.seller.infrastructure.persistence.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
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


    public void save(String sessionId, AuthenticatedMember authenticatedMember) {
        redisTemplate.opsForValue().set(sessionId, authenticatedMember);
    }

    public void remove(String sessionId) {
        redisTemplate.delete(sessionId);
    }

    public AuthenticatedMember findBySessionId(String sessionId) {
        var result = redisTemplate.opsForValue().get(sessionId);

        if (result == null) {
            return null;
        }

        return objectMapper.convertValue(result, AuthenticatedMember.class);
    }

    public void renewExpirationSec(AuthenticatedMember authenticatedMember) {
        long expirationTime = calculateExpirationTime(authenticatedMember);
        authenticatedMember.addExpirationSec(expirationTime);

        redisTemplate.opsForValue().set(
                authenticatedMember.getAuthId(),
                authenticatedMember,
                Duration.ofSeconds(sessionProperties.getSessionExpirationSec())
        );
    }

    private long calculateExpirationTime(AuthenticatedMember authenticatedMember) {
        Long sessionExpirationSec = sessionProperties.getSessionExpirationSec();
        Long sellerExpirationSec = redisTemplate.getExpire(
                authenticatedMember.getAuthId(),
                TimeUnit.SECONDS
        );

        if (sellerExpirationSec == null) {
            throw new IllegalArgumentException();
        }

        if (sessionExpirationSec - sellerExpirationSec <= 0) {
            return sessionExpirationSec;
        }

        return sessionExpirationSec - sellerExpirationSec;
    }
}
