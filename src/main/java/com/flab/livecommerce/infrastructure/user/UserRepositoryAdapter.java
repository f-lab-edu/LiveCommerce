package com.flab.livecommerce.infrastructure.user;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.user.persistence.inmemory.InMemoryUserRepository;
import com.flab.livecommerce.infrastructure.user.persistence.jdbctemplate.JdbcTemplateUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final JdbcTemplateUserRepository userRepository;

    public UserRepositoryAdapter(
        JdbcTemplateUserRepository jdbcTemplateUserRepository
    ) {
        this.userRepository = jdbcTemplateUserRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
