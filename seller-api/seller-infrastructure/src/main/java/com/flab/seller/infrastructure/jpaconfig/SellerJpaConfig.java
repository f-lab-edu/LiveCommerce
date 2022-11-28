package com.flab.seller.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.seller.domain")
@EnableJpaRepositories(basePackages = "com.flab.seller.infrastructure.persistence.jpa")
public class SellerJpaConfig {

}
