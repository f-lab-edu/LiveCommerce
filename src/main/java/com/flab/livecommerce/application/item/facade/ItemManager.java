package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.DeleteItemProcessor;
import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UpdateItemProcessor;
import com.flab.livecommerce.application.item.command.ItemFormCommand;
import com.flab.livecommerce.domain.item.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;
    private final SearchItemProcessor searchItemProcessor;
    private final UpdateItemProcessor updateItemProcessor;
    private final DeleteItemProcessor deleteItemProcessor;

    public ItemManager(
        RegisterItemProcessor registerItemProcessor,
        SearchItemProcessor searchItemProcessor,
        UpdateItemProcessor updateItemProcessor,
        DeleteItemProcessor deleteItemProcessor
    ) {
        this.registerItemProcessor = registerItemProcessor;
        this.searchItemProcessor = searchItemProcessor;
        this.updateItemProcessor = updateItemProcessor;
        this.deleteItemProcessor = deleteItemProcessor;
    }

    public Item register(ItemFormCommand command) {
        return registerItemProcessor.execute(command);
    }

    public Item search(Long id) {
        return searchItemProcessor.execute(id);
    }

    public void delete(Long id) {
        deleteItemProcessor.execute(id);
    }

    public void update(ItemFormCommand command, Long id) {
        updateItemProcessor.execute(command, id);
    }
}
