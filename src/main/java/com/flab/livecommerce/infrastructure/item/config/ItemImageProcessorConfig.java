package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.GetImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.domain.item.FileStorageService;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.image.LocalStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemImageProcessorConfig {


    @Bean
    public FileStorageService fileStorageService() {
        return new LocalStorageService();
    }

    @Bean
    public UploadImageProcessor uploadImageProcessor(
        ItemImageRepository itemImageRepository,
        ItemRepository itemRepository
    ) {
        return new UploadImageProcessor(itemImageRepository, itemRepository, fileStorageService());
    }

    @Bean
    public DeleteImageProcessor deleteImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new DeleteImageProcessor(itemImageRepository, fileStorageService());
    }

    @Bean
    public UpdateImagePriorityProcessor updateImagePriorityProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new UpdateImagePriorityProcessor(itemImageRepository, fileStorageService());
    }

    @Bean
    public GetImageProcessor getImageProcessor(
    ) {
        return new GetImageProcessor();
    }

}
