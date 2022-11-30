package com.flab.user.infrastructure.config;

import com.flab.user.application.CreateUserProcessor;
import com.flab.user.application.LoginUserProcessor;
import com.flab.user.application.LogoutUserProcessor;
import com.flab.user.domain.TokenGenerator;
import com.flab.user.domain.TokenRepository;
import com.flab.user.domain.UserRepository;
import com.flab.user.infrastructure.encryption.UserSecurityPasswordEncoder;
import com.flab.user.infrastructure.generator.NonInfoTokenGenerator;
import com.flab.user.infrastructure.tokenproperties.TokenProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public CreateUserProcessor createUserProcessor(
        UserRepository userRepository
    ) {
        return new CreateUserProcessor(
            userRepository,
            new UserSecurityPasswordEncoder(userEncodingAlgorithm())
        );
    }

    @Bean
    public LoginUserProcessor loginUserProcessor(
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository,
        TokenProperties tokenProperties
    ) {
        return new LoginUserProcessor(
            userRepository,
            tokenGenerator,
            tokenRepository,
            new UserSecurityPasswordEncoder(userEncodingAlgorithm()),
            tokenProperties.getTokenExpirationSec()
        );
    }

    @Bean
    public LogoutUserProcessor logoutUserProcessor(TokenRepository tokenRepository) {
        return new LogoutUserProcessor(tokenRepository);
    }

    @Bean
    public TokenGenerator tokenGenerator(
        TokenRepository tokenRepository
    ) {
        return new NonInfoTokenGenerator(tokenRepository);
    }

    @Bean
    public PasswordEncoder userEncodingAlgorithm() {
        return new BCryptPasswordEncoder();
    }
}
