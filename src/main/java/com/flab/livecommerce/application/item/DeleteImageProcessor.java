package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import java.util.List;

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


    public void execute(Long id, List<Integer> ordering) {
        itemImageRepository.deleteAll(id, ordering);
        //imageUploader.deleteAll(i);
    }
}
