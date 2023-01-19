package com.flab.point.infrastructure.jpaconfig;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.flab.point.domain")
@EnableJpaRepositories(basePackages = "com.flab.point.infrastructure.persistence.jpa")
public class PointTransactionJpaConfig {


}
