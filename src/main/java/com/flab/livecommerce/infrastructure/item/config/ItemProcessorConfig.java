package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.domain.item.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemProcessorConfig {

    @Bean
    public RegisterItemProcessor registerItemProcessor(
        ItemRepository itemRepository
    ) {
        return new RegisterItemProcessor(itemRepository);
    }
}
