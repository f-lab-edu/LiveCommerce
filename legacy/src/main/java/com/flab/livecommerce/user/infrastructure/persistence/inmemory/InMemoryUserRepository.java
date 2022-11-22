package com.flab.livecommerce.user.infrastructure.persistence.inmemory;


import com.flab.livecommerce.user.domain.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class InMemoryUserRepository {

    private static Map<Long, User> userMap = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    //todo 수정해야 할 부분 (외부에서 set 을 사용하여 private 에 접근)
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
