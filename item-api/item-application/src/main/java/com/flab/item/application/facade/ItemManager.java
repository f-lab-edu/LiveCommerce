package com.flab.item.application.facade;

import com.flab.item.application.RegisterItemProcessor;
import com.flab.item.application.command.RegisterItemCommand;
import com.flab.item.domain.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;

    public ItemManager(RegisterItemProcessor registerItemProcessor) {
        this.registerItemProcessor = registerItemProcessor;
    }

    public Item register(Long sellerId, RegisterItemCommand command) {
        registerItemProcessor.execute(sellerId, command);

        return null;
    }
}
