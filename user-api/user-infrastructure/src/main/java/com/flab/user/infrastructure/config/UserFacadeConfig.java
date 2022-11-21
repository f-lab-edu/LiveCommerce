package com.flab.user.infrastructure.config;

import com.flab.user.application.CreateUserProcessor;
import com.flab.user.application.LoginUserProcessor;
import com.flab.user.application.LogoutUserProcessor;
import com.flab.user.application.facade.UserManager;
import com.flab.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFacadeConfig {

    @Bean
    public UserManager userManager(
        CreateUserProcessor createUserProcessor,
        LoginUserProcessor loginUserProcessor,
        LogoutUserProcessor logoutUserProcessor,
        UserRepository userRepository
    ) {
        return new UserManager(
            createUserProcessor,
            loginUserProcessor,
            logoutUserProcessor,
            userRepository
        );
    }
}
