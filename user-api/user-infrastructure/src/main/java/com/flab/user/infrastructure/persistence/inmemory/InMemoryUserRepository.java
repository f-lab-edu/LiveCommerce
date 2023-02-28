package com.flab.user.infrastructure.persistence.inmemory;

import com.flab.user.domain.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository {

    private static Map<Long, User> userMap = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    public User save(User user) {
        user.setId(sequence.incrementAndGet());
        userMap.put(user.getId(), user);
        return user;
    }

    public User findByEmail(String email) {
        for (User user : userMap.values()) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }
}
