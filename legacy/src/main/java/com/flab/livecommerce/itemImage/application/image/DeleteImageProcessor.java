package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.image.ItemImageRepository;
import org.springframework.transaction.annotation.Transactional;

public class DeleteImageProcessor {

    private final ItemImageRepository itemImageRepository;


    public DeleteImageProcessor(
        ItemImageRepository itemImageRepository
    ) {
        this.itemImageRepository = itemImageRepository;
    }


    @Transactional
    public void execute(Long itemId) {
        itemImageRepository.deleteById(itemId);
    }
}
