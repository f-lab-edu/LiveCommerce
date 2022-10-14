package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImageRepository;
import java.util.List;

public class UpdateImagePriorityProcessor {

    private final ItemImageRepository itemImageRepository;

    private final ImageUploader imageUploader;


    public UpdateImagePriorityProcessor(
        ItemImageRepository itemImageRepository,
        ImageUploader imageUploader
    ) {
        this.itemImageRepository = itemImageRepository;
        this.imageUploader = imageUploader;
    }


    public void execute(Long itemId, List<Integer> orderList) {
        itemImageRepository.updateOrdering(itemId, orderList);
    }
}
