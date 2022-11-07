package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.application.item.DeleteImageProcessor;
import com.flab.livecommerce.application.item.UpdateImagePriorityProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.infrastructure.item.image.LocalUploader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemImageProcessorConfig {

    @Bean
    private static ImageUploader getUploader() {
        return new LocalUploader();
    }

    @Bean
    public UploadImageProcessor uploadImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new UploadImageProcessor(itemImageRepository, getUploader());
    }

    @Bean
    public DeleteImageProcessor deleteImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new DeleteImageProcessor(itemImageRepository, getUploader());
    }

    @Bean
    public UpdateImagePriorityProcessor updateImagePriorityProcessor(
        ItemImageRepository itemImageRepository
    ) {
        return new UpdateImagePriorityProcessor(itemImageRepository, getUploader());
    }


}
