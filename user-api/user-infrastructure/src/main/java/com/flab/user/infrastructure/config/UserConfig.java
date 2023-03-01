package com.flab.user.infrastructure.config;

import com.flab.user.application.CheckEmailProcessor;
import com.flab.user.application.CreateUserProcessor;
import com.flab.user.application.LoginUserProcessor;
import com.flab.user.application.LogoutUserProcessor;
import com.flab.user.domain.PasswordEncryptor;
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
        UserRepository userRepository,
        PasswordEncryptor passwordEncryptor
    ) {
        return new CreateUserProcessor(
            userRepository,
            passwordEncryptor
        );
    }

    @Bean
    public LoginUserProcessor loginUserProcessor(
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository,
        PasswordEncryptor passwordEncryptor,
        TokenProperties tokenProperties
    ) {
        return new LoginUserProcessor(
            userRepository,
            tokenGenerator,
            tokenRepository,
            passwordEncryptor,
            tokenProperties.getTokenExpirationSec()
        );
    }

    @Bean
    public CheckEmailProcessor emailProcessor(
        UserRepository userRepository
    ) {
        return new CheckEmailProcessor(userRepository);
    }

    @Bean
    public LogoutUserProcessor logoutUserProcessor(TokenRepository tokenRepository) {
        return new LogoutUserProcessor(tokenRepository);
    }

    @Bean
    public TokenGenerator tokenGenerator() {
        return new NonInfoTokenGenerator();
    }

    @Bean
    public UserSecurityPasswordEncoder passwordEncryptor(
        PasswordEncoder passwordEncoder
    ) {
        return new UserSecurityPasswordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
