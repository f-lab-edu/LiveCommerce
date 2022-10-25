package com.flab.livecommerce.infrastructure.user.persistence.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.livecommerce.common.AuthenticatedUser;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RedisTokenRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Value("${expire.default}")
    private long expireTime;

    public RedisTokenRepository(
        RedisTemplate<String, Object> redisTemplate,
        ObjectMapper objectMapper
    ) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void save(String token, AuthenticatedUser authenticatedUser) {
        redisTemplate.opsForValue().set(
            token,
            authenticatedUser.updateExpireTime(expireTime),
            Duration.ofSeconds(expireTime)
        );
    }

    public AuthenticatedUser findByToken(String token) {
        var result = redisTemplate.opsForValue().get(token);

        if (result == null) {
            return null;
        }

        var authenticatedUser = objectMapper.convertValue(result, AuthenticatedUser.class);

        redisTemplate.opsForValue().set(
            token,
            authenticatedUser.updateExpireTime(expireTime),
            Duration.ofSeconds(expireTime)
        );

        return authenticatedUser;
    }

    public void remove(String token) {
        redisTemplate.delete(token);
    }
}
