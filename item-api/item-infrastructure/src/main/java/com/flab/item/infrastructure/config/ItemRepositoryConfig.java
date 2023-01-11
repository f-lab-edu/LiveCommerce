package com.flab.item.infrastructure.config;

import com.flab.item.domain.ItemRepository;
import com.flab.item.infrastructure.persistence.ItemRepositoryAdapter;
import com.flab.item.infrastructure.persistence.jpa.JpaItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemRepositoryConfig {

    @Bean
    public ItemRepository itemRepository(JpaItemRepository jpaItemRepository) {
        return new ItemRepositoryAdapter(jpaItemRepository);
    }
}
