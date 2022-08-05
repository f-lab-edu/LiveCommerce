package com.flab.livecommerce.infrastructure.config;

import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

    @Bean
    public UserCreateProcessor userCreateProcessor(
            UserRepository userRepository
    ) {
        return new UserCreateProcessor(userRepository);
    }
}
