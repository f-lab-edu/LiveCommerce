package com.flab.livecommerce.infrastructure.user.redis;

import com.flab.livecommerce.common.AuthenticatedUser;
import com.flab.livecommerce.domain.user.User;
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

    public void save(String token, User user) {
        redisTemplate.opsForValue().set(
            token,
            new AuthenticatedUser(
                user.getId(),
                user.getEmail(),
                user.getRole()
            )
        );
        expireTimeUpdate(token);
    }

    public AuthenticatedUser findByToken(String token) {
        var authenticatedUser = redisTemplate.opsForValue().get(token);

        if (authenticatedUser != null) {
            expireTimeUpdate(token);
        }

        return (AuthenticatedUser) authenticatedUser;
    }

    public void remove(String token) {
        redisTemplate.delete(token);
    }

    private void expireTimeUpdate(String token) {
        redisTemplate.expire(token, AuthenticatedUser.EXPIRE_TIME, TimeUnit.SECONDS);
    }
}
