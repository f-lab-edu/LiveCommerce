package com.flab.livecommerce.user.application;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class FakeUserRepository implements UserRepository {

    private final Map<Long, User> data = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public User save(User user) {
        return data.put(idGenerator.incrementAndGet(), user);
    }

    @Override
    public User findByEmail(String email) {
        return data.values().stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return data.values().stream()
            .anyMatch(user -> user.getEmail().equals(email));
    }
}
