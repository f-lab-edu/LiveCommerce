package com.flab.livecommerce.infrastructure.user.persistence.redis;

import com.flab.livecommerce.domain.user.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisTokenRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTokenRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String token, User user) {
        redisTemplate.opsForValue().set(token, user);
    }

    public User findByToken(String token) {
        return (User) redisTemplate.opsForValue().get(token);
    }

    public void remove(String token) {
        redisTemplate.delete(token);
    }
}
