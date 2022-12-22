package com.flab.inventory.infrastructure.config;

import com.flab.inventory.application.OrderPayedProcessor;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.OrderReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Bean
    OrderPayedProcessor orderPayedProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new OrderPayedProcessor(inventoryRepository);
    }

}
