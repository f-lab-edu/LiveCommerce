package com.flab.item.infrastructure.config;

import com.flab.item.application.RegisterItemProcessor;
import com.flab.item.domain.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfig {

    @Bean
    public RegisterItemProcessor registerItemProcessor(
        ItemRepository itemRepository
    ) {
        return new RegisterItemProcessor(itemRepository);
    }
}
