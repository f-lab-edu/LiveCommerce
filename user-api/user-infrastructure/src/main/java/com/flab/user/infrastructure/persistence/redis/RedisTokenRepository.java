package com.flab.user.infrastructure.persistence.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.common.auth.AuthenticatedMember;
import com.flab.user.infrastructure.tokenproperties.TokenProperties;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisTokenRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final TokenProperties tokenProperties;

    public RedisTokenRepository(
            RedisTemplate<String, Object> redisTemplate,
            ObjectMapper objectMapper,
            TokenProperties tokenProperties
    ) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.tokenProperties = tokenProperties;
    }

    public void save(AuthenticatedMember authenticatedMember) {
        redisTemplate.opsForValue().set(
                authenticatedMember.getAuthId(),
                authenticatedMember,
                Duration.ofSeconds(tokenProperties.getTokenExpirationSec())
        );
    }

    public AuthenticatedMember findByToken(String token) {
        var result = redisTemplate.opsForValue().get(token);

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
                Duration.ofSeconds(tokenProperties.getTokenExpirationSec())
        );
    }

    private long calculateExpirationTime(AuthenticatedMember authenticatedMember) {
        Long propertiesExpirationSec = tokenProperties.getTokenExpirationSec();
        Long userExpirationSec = redisTemplate.getExpire(
                authenticatedMember.getAuthId(),
                TimeUnit.SECONDS
        );

        if (userExpirationSec == null) {
            throw new IllegalArgumentException();
        }

        if (propertiesExpirationSec - userExpirationSec <= 0) {
            return tokenProperties.getTokenExpirationSec();
        }

        return propertiesExpirationSec - userExpirationSec;
    }

    public void remove(String token) {
        redisTemplate.delete(token);
    }

}
