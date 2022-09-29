package com.flab.livecommerce.infrastructure.shop.config;

import com.flab.livecommerce.application.shop.RegisterShopProcessor;
import com.flab.livecommerce.domain.shop.ShopRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopProcessorConfig {

    @Bean
    public RegisterShopProcessor registerPartnerProcessor(
        ShopRepository shopRepository
    ) {
        return new RegisterShopProcessor(
            shopRepository
        );
    }
}
