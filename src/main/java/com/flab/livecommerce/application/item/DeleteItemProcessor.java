package com.flab.livecommerce.application.item;

import com.flab.livecommerce.domain.item.ItemImageRepository;
import com.flab.livecommerce.domain.item.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

public class DeleteItemProcessor {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;


    public DeleteItemProcessor(ItemRepository itemRepository,
        ItemImageRepository itemImageRepository) {
        this.itemRepository = itemRepository;
        this.itemImageRepository = itemImageRepository;
    }

    @Transactional
    public void execute(Long id) {
        itemImageRepository.deleteById(id);
        itemRepository.deleteById(id);
    }
}
