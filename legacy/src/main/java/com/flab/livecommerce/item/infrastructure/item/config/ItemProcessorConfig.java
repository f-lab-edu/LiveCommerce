package com.flab.livecommerce.item.infrastructure.item.config;

<<<<<<< HEAD:toMove/infrastructure/item/config/ItemProcessorConfig.java

import com.flab.livecommerce.application.item.DeleteItemProcessor;
import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UpdateItemProcessor;
import com.flab.livecommerce.domain.image.FileUriPrefixGenerator;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.ItemOptionSeriesServiceImpl;
=======
import com.flab.livecommerce.item.application.RegisterItemProcessor;
import com.flab.livecommerce.item.application.SearchItemProcessor;
import com.flab.livecommerce.item.application.UploadImageProcessor;
import com.flab.livecommerce.item.domain.ItemImageRepository;
import com.flab.livecommerce.item.domain.ItemOptionGroupRepository;
import com.flab.livecommerce.item.domain.ItemOptionRepository;
import com.flab.livecommerce.item.domain.ItemOptionSeriesService;
import com.flab.livecommerce.item.domain.ItemRepository;
import com.flab.livecommerce.item.infrastructure.item.ItemOptionSeriesServiceImpl;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/infrastructure/item/config/ItemProcessorConfig.java
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
        ItemRepository itemRepository
    ) {
        return new DeleteItemProcessor(itemRepository);
    }

    @Bean
    public SearchItemProcessor searchItemProcessor(
        ItemRepository itemRepository,
        FileUriPrefixGenerator fileUriPrefixGenerator
    ) {
        return new SearchItemProcessor(itemRepository, fileUriPrefixGenerator.generate());
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
