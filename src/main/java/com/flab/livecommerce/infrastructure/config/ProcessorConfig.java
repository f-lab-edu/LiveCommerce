package com.flab.livecommerce.infrastructure.config;

import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.encryption.SecurityPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProcessorConfig {

    @Bean
    private static PasswordEncoder algorithm() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserCreateProcessor userCreateProcessor(
            UserRepository userRepository
    ) {
        return new UserCreateProcessor(
                userRepository,
                new SecurityPasswordEncoder(algorithm())
        );
    }

    @Bean
    public UserLoginProcessor userLoginProcessor(
            UserRepository userRepository
    ) {
        return new UserLoginProcessor(
                userRepository,
                new SecurityPasswordEncoder(algorithm())
        );
    }
}
