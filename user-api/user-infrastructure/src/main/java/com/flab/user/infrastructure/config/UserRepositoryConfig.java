package com.flab.user.infrastructure.config;

import com.flab.user.domain.UserRepository;
import com.flab.user.infrastructure.UserRepositoryAdapter;
import com.flab.user.infrastructure.persistence.jpa.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfig {

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return new UserRepositoryAdapter(jpaUserRepository);
    }
}
