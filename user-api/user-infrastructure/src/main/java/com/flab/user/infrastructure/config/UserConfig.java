package com.flab.user.infrastructure.config;

import com.flab.user.application.CreateUserProcessor;
import com.flab.user.application.LoginUserProcessor;
import com.flab.user.application.LogoutUserProcessor;
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
    public CreateUserProcessor createUserProcessor(
        UserRepository userRepository
    ) {
        return new CreateUserProcessor(
            userRepository,
            new SecurityPasswordEncoder(algorithm2())
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
            new SecurityPasswordEncoder(algorithm2()),
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

    // TODO encryption 공통화 처리 (리팩토링 필요)
    @Bean
    public PasswordEncoder algorithm2() {
        return new BCryptPasswordEncoder();
    }
}
