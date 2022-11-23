package com.flab.livecommerce.infrastructure.image.config;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.GetImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.domain.image.FileStorageService;
import com.flab.livecommerce.domain.image.FileUriPrefixGenerator;
import com.flab.livecommerce.domain.image.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.infrastructure.image.ImageProperties;
import com.flab.livecommerce.infrastructure.image.local.LocalStorageService;
import com.flab.livecommerce.infrastructure.image.local.LocalUriPrefixGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemImageProcessorConfig {


    @Bean
    public static FileStorageService fileStorageService(
        ImageProperties imageProperties
    ) {
        return new LocalStorageService(imageProperties.getLocalPath());
    }

    @Bean
    public static FileUriPrefixGenerator fileUriGenerator() {
        return new LocalUriPrefixGenerator();
    }


    @Bean
    public UploadImageProcessor uploadImageProcessor(
        ItemImageRepository itemImageRepository,
        ItemRepository itemRepository,
        ImageProperties imageProperties
    ) {
        return new UploadImageProcessor(
            itemImageRepository,
            itemRepository,
            fileStorageService(imageProperties),
            fileUriGenerator().generate());
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
        ImageProperties imageProperties
    ) {
        return new GetImageProcessor(imageProperties.getLocalPath());
    }

}
