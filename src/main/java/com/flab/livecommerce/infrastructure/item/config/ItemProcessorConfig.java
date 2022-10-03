package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemOptionGroupRepository;
import com.flab.livecommerce.domain.item.ItemOptionRepository;
import com.flab.livecommerce.domain.item.ItemOptionSeriesService;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.ItemOptionSeriesServiceImpl;
import com.flab.livecommerce.infrastructure.item.LocalUploader;
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

    /*
     * AWS S3 서비스 사용 시 이미지 업로더 구현체 변경
     */
    @Bean
    public UploadImageProcessor uploadImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new UploadImageProcessor(itemImageRepository, new LocalUploader());
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
