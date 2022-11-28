package com.flab.inventory.infrastructure.config;

import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.infrastructure.persistence.InventoryRepositoryAdapter;
import com.flab.inventory.infrastructure.persistence.jpa.JpaInventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryRepositoryConfig {

    @Bean
    public InventoryRepository inventoryRepository(JpaInventoryRepository jpaInventoryRepository) {
        return new InventoryRepositoryAdapter(jpaInventoryRepository);
    }

}
