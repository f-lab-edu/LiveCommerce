package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import org.springframework.transaction.annotation.Transactional;

public class DeleteImageProcessor {

    private final ItemImageRepository itemImageRepository;
    private final ImageUploader imageUploader;


    public DeleteImageProcessor(
        ItemImageRepository itemImageRepository,
        ImageUploader imageUploader
    ) {
        this.itemImageRepository = itemImageRepository;
        this.imageUploader = imageUploader;
    }


    @Transactional
    public void execute(Long itemId) {
        itemImageRepository.deleteById(itemId);
    }
}
