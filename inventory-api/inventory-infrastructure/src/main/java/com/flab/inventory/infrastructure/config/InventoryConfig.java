package com.flab.inventory.infrastructure.config;

import com.flab.inventory.application.CloseInventoryProcessor;
import com.flab.inventory.application.OrderPayedProcessor;
import com.flab.inventory.application.UpdateInventoryProcessor;
import com.flab.inventory.domain.InventoryRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Bean
    OrderPayedProcessor orderPayedProcessor(
        InventoryRepository inventoryRepository,
        ApplicationEventPublisher publisher
    ) {
        return new OrderPayedProcessor(inventoryRepository, publisher);
    }

    @Bean
    UpdateInventoryProcessor updateInventoryProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new UpdateInventoryProcessor(inventoryRepository);
    }

    @Bean
    CloseInventoryProcessor closeInventoryProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new CloseInventoryProcessor(inventoryRepository);
    }

}
