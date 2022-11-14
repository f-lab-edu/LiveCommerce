package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.FileStorageService;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import org.springframework.transaction.annotation.Transactional;

public class DeleteImageProcessor {

    private final ItemImageRepository itemImageRepository;
    private final FileStorageService fileStorageService;


    public DeleteImageProcessor(
        ItemImageRepository itemImageRepository,
        FileStorageService fileStorageService
    ) {
        this.itemImageRepository = itemImageRepository;
        this.fileStorageService = fileStorageService;
    }


    @Transactional
    public void execute(Long itemId) {
        itemImageRepository.deleteById(itemId);
    }
}
