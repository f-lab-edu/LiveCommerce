package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.UpdateImageOrderCommand;
import com.flab.livecommerce.domain.item.ImageUploader;
import com.flab.livecommerce.domain.item.ItemImageRepository;

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


    public void execute(UpdateImageOrderCommand command) {
        int requestOrderSize = command.getImageIdList().size();
        for (int i = 0; i < requestOrderSize; i++) {
            itemImageRepository.updateOrder(command.getImageIdList().get(i), command.getOrderingList().get(i));
        }
    }
}
