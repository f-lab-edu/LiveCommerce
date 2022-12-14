package com.flab.payment.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.payment.domain")
@EnableJpaRepositories(basePackages = "com.flab.payment.infrastructure.persistence.jpa")
public class PaymentJpaConfig {

}
