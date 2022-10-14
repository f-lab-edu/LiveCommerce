package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.DeleteItemProcessor;
import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UpdateItemProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.ItemOptionSeriesServiceImpl;
import com.flab.livecommerce.infrastructure.item.image.LocalUploader;
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
    public UpdateItemProcessor updateItemProcessor(
        ItemRepository itemRepository,
        ItemOptionSeriesService itemOptionSeriesService
    ) {
        return new UpdateItemProcessor(
            itemRepository,
            itemOptionSeriesService
        );
    }

    @Bean
    public DeleteItemProcessor deleteItemProcessor(
        ItemRepository itemRepository,
        ItemImageRepository itemImageRepository
    ) {
        return new DeleteItemProcessor(itemRepository, itemImageRepository);
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
