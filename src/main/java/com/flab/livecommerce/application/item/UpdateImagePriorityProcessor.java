package com.flab.livecommerce.application.item;

import com.flab.livecommerce.application.item.command.UpdateImageOrderCommand;
import com.flab.livecommerce.domain.item.FileStorageService;
import com.flab.livecommerce.domain.item.ItemImageRepository;

public class UpdateImagePriorityProcessor {

    private final ItemImageRepository itemImageRepository;

    private final FileStorageService fileStorageService;


    public UpdateImagePriorityProcessor(
        ItemImageRepository itemImageRepository,
        FileStorageService fileStorageService
    ) {
        this.itemImageRepository = itemImageRepository;
        this.fileStorageService = fileStorageService;
    }


    public void execute(UpdateImageOrderCommand command) {
        int requestOrderSize = command.getImageIdList().size();
        for (int i = 0; i < requestOrderSize; i++) {
            itemImageRepository.updateOrder(command.getImageIdList().get(i), command.getOrderingList().get(i));
        }
    }
}
