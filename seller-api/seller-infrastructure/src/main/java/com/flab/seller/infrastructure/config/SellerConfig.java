package com.flab.seller.infrastructure.config;

import com.flab.seller.application.CreateSellerProcessor;
import com.flab.seller.application.LoginSellerProcessor;
import com.flab.seller.application.SearchSellerProcessor;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.infrastructure.encryption.SellerSecurityPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SellerConfig {

    @Bean
    public CreateSellerProcessor createSellerProcessor(
        SellerRepository sellerRepository
    ) {
        return new CreateSellerProcessor(
            sellerRepository,
            new SellerSecurityPasswordEncoder(sellerEncodingAlgorithm()));
    }

    @Bean
    public LoginSellerProcessor loginSellerProcessor(
        SellerRepository sellerRepository
    ) {
        return new LoginSellerProcessor(
            sellerRepository,
            new SellerSecurityPasswordEncoder(sellerEncodingAlgorithm())
        );
    }

    @Bean
    public SearchSellerProcessor searchSellerProcessor(
        SellerRepository sellerRepository
    ) {
        return new SearchSellerProcessor(
            sellerRepository
        );
    }

    @Bean
    public PasswordEncoder sellerEncodingAlgorithm() {
        return new BCryptPasswordEncoder();
    }
}
