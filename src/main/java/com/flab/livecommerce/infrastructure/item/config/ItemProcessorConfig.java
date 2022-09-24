package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.ItemOptionSeriesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemProcessorConfig {

    @Bean
    public RegisterItemProcessor registerItemProcessor(
        ItemRepository itemRepository,
        ItemOptionSeriesService itemOptionSeriesService
    ) {
        return new RegisterItemProcessor(
            itemRepository,
            itemOptionSeriesService
        );
    }

    @Bean
    public ItemOptionSeriesService itemOptionSeriesService(
        ItemOptionGroupRepository itemOptionGroupRepository,
        ItemOptionRepository itemOptionRepository
    ) {
        return new ItemOptionSeriesServiceImpl(
            itemOptionGroupRepository,
            itemOptionRepository
        );
    }
}
