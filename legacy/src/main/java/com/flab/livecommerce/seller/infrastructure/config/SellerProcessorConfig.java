package com.flab.livecommerce.seller.infrastructure.config;

import com.flab.livecommerce.seller.application.RegisterSellerProcessor;
import com.flab.livecommerce.seller.application.SearchSellerProcessor;
import com.flab.livecommerce.seller.domain.SellerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SellerProcessorConfig {

    @Bean
    public RegisterSellerProcessor registerPartnerProcessor(
        SellerRepository sellerRepository
    ) {
        return new RegisterSellerProcessor(
            sellerRepository
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
}
