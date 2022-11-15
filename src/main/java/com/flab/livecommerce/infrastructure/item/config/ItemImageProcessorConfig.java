package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.GetImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.domain.image.FileClient;
import com.flab.livecommerce.domain.image.FileStorageService;
import com.flab.livecommerce.domain.image.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.item.image.local.LocalClient;
import com.flab.livecommerce.infrastructure.item.image.local.LocalStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemImageProcessorConfig {

    @Bean
    public FileClient fileClient() {
        return new LocalClient();
    }

    @Bean
    public FileStorageService fileStorageService(
    ) {
        return new LocalStorageService(fileClient());
    }

    @Bean
    public UploadImageProcessor uploadImageProcessor(
        ItemImageRepository itemImageRepository,
        ItemRepository itemRepository
    ) {
        return new UploadImageProcessor(
            itemImageRepository,
            itemRepository,
            fileStorageService(),
            fileClient()
        );
    }


    @Bean
    public DeleteImageProcessor deleteImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new DeleteImageProcessor(itemImageRepository);
    }

    @Bean
    public UpdateImagePriorityProcessor updateImagePriorityProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new UpdateImagePriorityProcessor(itemImageRepository);
    }

    @Bean
    public GetImageProcessor getImageProcessor(
    ) {
        return new GetImageProcessor();
    }

}
