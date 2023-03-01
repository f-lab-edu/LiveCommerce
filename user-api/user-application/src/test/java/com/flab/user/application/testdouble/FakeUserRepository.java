package com.flab.user.application.testdouble;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.common.exception.ErrorCode;
import com.flab.user.domain.User;
import com.flab.user.domain.UserRepository;
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
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public boolean existsByEmail(String email) {
        return data.values().stream()
            .anyMatch(user -> user.getEmail().equals(email));
    }
}
