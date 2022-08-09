package com.flab.livecommerce.infrastructure.config;

import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.TokenAuthorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProcessorConfig {

    @Bean
    public UserCreateProcessor userCreateProcessor(
        UserRepository userRepository
    ) {
        return new UserCreateProcessor(userRepository, passwordEncoder());
    }

    @Bean
    public UserLoginProcessor userLoginProcessor(
        UserRepository userRepository
    ) {
        return new UserLoginProcessor(userRepository, passwordEncoder());
    }

    @Bean
    public TokenAuthorization tokenAuthorization() {
        return new TokenAuthorization();
    }

    private static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
