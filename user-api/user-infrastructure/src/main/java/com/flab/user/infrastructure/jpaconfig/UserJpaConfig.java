package com.flab.user.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.user.domain")
@EnableJpaRepositories(basePackages = "com.flab.user.infrastructure.persistence.jpa")
public class UserJpaConfig {

}
