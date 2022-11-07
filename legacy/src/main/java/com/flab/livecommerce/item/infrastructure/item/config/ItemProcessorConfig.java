package com.flab.livecommerce.item.infrastructure.item.config;

import com.flab.livecommerce.item.application.RegisterItemProcessor;
import com.flab.livecommerce.item.application.SearchItemProcessor;
import com.flab.livecommerce.item.application.UploadImageProcessor;
import com.flab.livecommerce.item.domain.ItemImageRepository;
import com.flab.livecommerce.item.domain.ItemOptionGroupRepository;
import com.flab.livecommerce.item.domain.ItemOptionRepository;
import com.flab.livecommerce.item.domain.ItemOptionSeriesService;
import com.flab.livecommerce.item.domain.ItemRepository;
import com.flab.livecommerce.item.infrastructure.item.ItemOptionSeriesServiceImpl;
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
    public UploadImageProcessor uploadImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new UploadImageProcessor(itemImageRepository);
    }

    @Bean
    public SearchItemProcessor searchItemProcessor(
        ItemRepository itemRepository
    ) {
        return new SearchItemProcessor(itemRepository);
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
