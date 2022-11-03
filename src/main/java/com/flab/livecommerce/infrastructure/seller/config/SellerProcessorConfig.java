package com.flab.livecommerce.infrastructure.seller.config;

import com.flab.livecommerce.application.seller.RegisterSellerProcessor;
import com.flab.livecommerce.application.seller.SearchSellerProcessor;
import com.flab.livecommerce.domain.seller.SellerRepository;
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
