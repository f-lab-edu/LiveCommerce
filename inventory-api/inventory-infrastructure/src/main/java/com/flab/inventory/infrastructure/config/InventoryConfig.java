package com.flab.inventory.infrastructure.config;

import com.flab.inventory.application.CloseInventoryProcessor;
import com.flab.inventory.application.IncreaseInventoryProcessor;
import com.flab.inventory.application.OpenInventoryProcessor;
import com.flab.inventory.application.OrderPayedProcessor;
import com.flab.inventory.application.ReduceInventoryProcessor;
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
    OpenInventoryProcessor openInventoryProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new OpenInventoryProcessor(inventoryRepository);
    }

    @Bean
    CloseInventoryProcessor closeInventoryProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new CloseInventoryProcessor(inventoryRepository);
    }

    @Bean
    IncreaseInventoryProcessor increaseInventoryProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new IncreaseInventoryProcessor(inventoryRepository);
    }

    @Bean
    ReduceInventoryProcessor reduceInventoryProcessor(
        InventoryRepository inventoryRepository
    ) {
        return new ReduceInventoryProcessor(inventoryRepository);
    }
}
