package com.flab.seller.infrastructure.config;

import com.flab.seller.domain.SellerRepository;
import com.flab.seller.infrastructure.persistence.SellerRepositoryAdapter;
import com.flab.seller.infrastructure.persistence.jpa.JpaSellerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SellerRepositoryConfig {

    @Bean
    public SellerRepository sellerRepository(JpaSellerRepository jpaSellerRepository) {
        return new SellerRepositoryAdapter(jpaSellerRepository);
    }
}
