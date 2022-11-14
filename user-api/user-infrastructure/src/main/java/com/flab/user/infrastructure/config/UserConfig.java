package com.flab.user.infrastructure.config;

import com.flab.user.application.UserCreateProcessor;
import com.flab.user.application.UserLoginProcessor;
import com.flab.user.domain.TokenGenerator;
import com.flab.user.domain.TokenRepository;
import com.flab.user.domain.UserRepository;
import com.flab.user.infrastructure.encryption.SecurityPasswordEncoder;
import com.flab.user.infrastructure.generator.NonInfoTokenGenerator;
import com.flab.user.infrastructure.tokenproperties.TokenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

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
            tokenProperties.getTokenExpirationSec()
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
