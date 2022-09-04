package com.flab.livecommerce.infrastructure.user;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.user.persistence.inmemory.InMemoryUserRepository;
import com.flab.livecommerce.infrastructure.user.persistence.mysql.MysqlUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final InMemoryUserRepository inMemoryUserRepository;
    private final MysqlUserRepository mysqlUserRepository;

    public UserRepositoryAdapter(
        InMemoryUserRepository inMemoryUserRepository,
        MysqlUserRepository mysqlUserRepository
    ) {
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.mysqlUserRepository = mysqlUserRepository;
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
