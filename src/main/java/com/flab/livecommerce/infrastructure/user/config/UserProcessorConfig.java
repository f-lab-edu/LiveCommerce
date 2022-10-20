package com.flab.livecommerce.infrastructure.user.config;

import com.flab.livecommerce.application.user.UserCreateProcessor;
import com.flab.livecommerce.application.user.UserLoginProcessor;
import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.user.encryption.SecurityPasswordEncoder;
import com.flab.livecommerce.infrastructure.user.generator.NonInfoTokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserProcessorConfig {

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
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository
    ) {
        return new UserLoginProcessor(
            userRepository,
            tokenGenerator,
            tokenRepository,
            new SecurityPasswordEncoder(algorithm())
        );
    }

    @Bean
    public TokenGenerator tokenGenerator(
        TokenRepository tokenRepository
    ) {
        return new NonInfoTokenGenerator(tokenRepository);
    }

    @Bean
    private static PasswordEncoder algorithm() {
        return new BCryptPasswordEncoder();
    }
}
