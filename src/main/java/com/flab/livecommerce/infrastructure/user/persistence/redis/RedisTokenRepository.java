package com.flab.livecommerce.infrastructure.user.persistence.redis;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RedisTokenRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTokenRepository(
        RedisTemplate<String, Object> redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String token, String userId) {
        redisTemplate.opsForValue().set(token, userId);
        redisTemplate.expire(token, 1000L, TimeUnit.SECONDS);
    }

    public String findByToken(String token) {
        return (String) redisTemplate.opsForValue().get(token);
    }

    public void remove(String token) {
        redisTemplate.delete(token);
    }
}
