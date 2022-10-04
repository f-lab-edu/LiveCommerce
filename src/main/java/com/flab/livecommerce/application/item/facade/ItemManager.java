package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UpdateItemProcessor;
import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;
    private final SearchItemProcessor searchItemProcessor;
    private final UpdateItemProcessor updateItemProcessor;
    private final ItemRepository itemRepository;

    public ItemManager(
        RegisterItemProcessor registerItemProcessor,
        SearchItemProcessor searchItemProcessor,
        UpdateItemProcessor updateItemProcessor,
        ItemRepository itemRepository
    ) {
        this.registerItemProcessor = registerItemProcessor;
        this.searchItemProcessor = searchItemProcessor;
        this.updateItemProcessor = updateItemProcessor;
        this.itemRepository = itemRepository;
    }

    public Item register(RegisterItemCommand command) {
        return registerItemProcessor.execute(command);
    }

    public Item search(Long id) {
        return searchItemProcessor.execute(id);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public void update(RegisterItemCommand command, Long id) {
        updateItemProcessor.execute(command, id);
    }
}
