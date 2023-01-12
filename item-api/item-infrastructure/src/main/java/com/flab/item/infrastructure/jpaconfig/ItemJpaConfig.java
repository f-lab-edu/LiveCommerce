package com.flab.item.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.item.domain")
@EnableJpaRepositories(basePackages = "com.flab.item.infrastructure.persistence.jpa")
public class ItemJpaConfig {

}
