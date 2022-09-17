package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.ItemAddProcessor;
import com.flab.livecommerce.domain.item.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConfig {

    @Bean
    public ItemAddProcessor productAddProcessor(
        ItemRepository itemRepository
    ) {
        return new ItemAddProcessor(itemRepository);
    }
}
