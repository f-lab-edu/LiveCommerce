package com.flab.livecommerce.infrastructure.persistence.inmemory;


import com.flab.livecommerce.domain.user.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;


@Repository
public class InMemoryUserRepository {

    private static Map<Long, User> userMap = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    public User save(User user) {
        user.setId(++sequence);
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
