package com.flab.livecommerce.infrastructure.product.config;

import com.flab.livecommerce.application.product.ProductAddProcessor;
import com.flab.livecommerce.domain.product.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

    @Bean
    public ProductAddProcessor productAddProcessor(
        ProductRepository productRepository
    ) {
        return new ProductAddProcessor(productRepository);
    }
}
