package com.flab.livecommerce.infrastructure.config;

import com.flab.livecommerce.application.UserCheckProcessor;
import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.TokenAuthorization;
import com.flab.livecommerce.infrastructure.encryption.SecurityPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {


    @Bean
    public UserCreateProcessor userCreateProcessor(
            UserRepository userRepository
    ) {
        return new UserCreateProcessor(userRepository, new SecurityPasswordEncoder() {
        });
    }

    @Bean
    public UserLoginProcessor userLoginProcessor(
            UserRepository userRepository
    ) {
        return new UserLoginProcessor(userRepository, new SecurityPasswordEncoder());
    }

    @Bean
    public UserCheckProcessor userCheckProcessor(
            UserRepository userRepository
    ) {
        return new UserCheckProcessor(userRepository);
    }

    @Bean
    public TokenAuthorization tokenAuthorization() {
        return new TokenAuthorization();
    }
}
