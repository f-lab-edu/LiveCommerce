package com.flab.livecommerce.user.infrastructure.config;

import com.flab.livecommerce.user.application.UserCreateProcessor;
import com.flab.livecommerce.user.application.UserLoginProcessor;
import com.flab.livecommerce.user.domain.TokenGenerator;
import com.flab.livecommerce.user.domain.TokenRepository;
import com.flab.livecommerce.user.domain.UserRepository;
import com.flab.livecommerce.user.infrastructure.encryption.SecurityPasswordEncoder;
import com.flab.livecommerce.user.infrastructure.generator.NonInfoTokenGenerator;
import com.flab.livecommerce.user.infrastructure.token.TokenProperties;
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
        TokenRepository tokenRepository,
        TokenProperties tokenProperties
    ) {
        return new UserLoginProcessor(
            userRepository,
            tokenGenerator,
            tokenRepository,
            new SecurityPasswordEncoder(algorithm()),
            tokenProperties
        );
    }

    @Bean
    public TokenGenerator tokenGenerator(
        TokenRepository tokenRepository
    ) {
        return new NonInfoTokenGenerator(tokenRepository);
    }

    @Bean
    public PasswordEncoder algorithm() {
        return new BCryptPasswordEncoder();
    }
}
