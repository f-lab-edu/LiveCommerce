package com.flab.livecommerce.infrastructure.persistence.inmemory;


import com.flab.livecommerce.domain.user.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;


@Repository
public class InMemoryUserRepository {

    private Map<String, User> map = new ConcurrentHashMap<>();

    public User save(User user) {
        map.put(user.getEmail(), user);
        return user;
    }

    public User findByEmail(String email) {
        return map.get(email);
    }
}
