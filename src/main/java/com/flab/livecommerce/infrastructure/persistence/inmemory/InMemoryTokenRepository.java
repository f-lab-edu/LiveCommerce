package com.flab.livecommerce.infrastructure.persistence.inmemory;

import com.flab.livecommerce.domain.user.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTokenRepository {

    private static Map<String, User> tokenMap = new ConcurrentHashMap<>();

    public void save(String token, User user) {
        tokenMap.put(token, user);
    }

    public User findByToken(String token) {
        return tokenMap.get(token);
    }
}
