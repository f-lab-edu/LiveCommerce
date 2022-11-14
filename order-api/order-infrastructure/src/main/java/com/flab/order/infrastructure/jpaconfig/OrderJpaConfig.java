package com.flab.order.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.order.domain")
@EnableJpaRepositories(basePackages = "com.flab.order.infrastructure.persistence.jpa")
public class OrderJpaConfig {

}
