package com.flab.livecommerce.application.item;

import com.flab.livecommerce.common.event.Events;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.item.event.ItemDeletedEvent;
import org.springframework.transaction.annotation.Transactional;

public class DeleteItemProcessor {

    private final ItemRepository itemRepository;


    public DeleteItemProcessor(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void execute(Long id) {
        itemRepository.deleteById(id);
        Events.raise(new ItemDeletedEvent(id));
    }
}
