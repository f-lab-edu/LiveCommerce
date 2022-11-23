package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.UpdateImageOrderingCommand;
import com.flab.livecommerce.domain.image.ItemImageRepository;

public class UpdateImagePriorityProcessor {

    private final ItemImageRepository itemImageRepository;



    public UpdateImagePriorityProcessor(
        ItemImageRepository itemImageRepository
    ) {
        this.itemImageRepository = itemImageRepository;
    }


    public void execute(UpdateImageOrderingCommand command) {
        int requestOrderSize = command.getImageIdList().size();
        for (int i = 0; i < requestOrderSize; i++) {
            itemImageRepository.updateOrdering(command.getImageIdList().get(i), command.getOrderingList().get(i));
        }
    }
}
