package com.flab.livecommerce.infrastructure;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final InMemoryUserRepository inMemoryUserRepository;

    public UserRepositoryAdapter(InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    @Override
    public User save(User user) {
        return this.inMemoryUserRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return this.inMemoryUserRepository.findByEmail(email);
    }
}
