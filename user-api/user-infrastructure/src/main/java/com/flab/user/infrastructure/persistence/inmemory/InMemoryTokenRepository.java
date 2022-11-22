package com.flab.user.infrastructure.persistence.inmemory;

import com.flab.user.domain.User;
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

    public void remove(String token) {
        tokenMap.remove(token);
    }
}
