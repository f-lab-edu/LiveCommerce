package com.flab.inventory.application.facade;

import com.flab.inventory.application.CloseInventoryProcessor;
import com.flab.inventory.application.IncreaseInventoryProcessor;
import com.flab.inventory.application.OpenInventoryProcessor;
import com.flab.inventory.application.ReduceInventoryProcessor;
import com.flab.inventory.application.UpdateInventoryProcessor;
import com.flab.inventory.application.command.CloseInventoryCommand;
import com.flab.inventory.application.command.IncreaseInventoryCommand;
import com.flab.inventory.application.command.OpenInventoryCommand;
import com.flab.inventory.application.command.ReduceInventoryCommand;
import com.flab.inventory.application.command.UpdateInventoryCommand;
import com.flab.inventory.domain.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryManager {

    private final UpdateInventoryProcessor updateInventoryProcessor;
    private final OpenInventoryProcessor openInventoryProcessor;
    private final CloseInventoryProcessor closeInventoryProcessor;
    private final IncreaseInventoryProcessor increaseInventoryProcessor;
    private final ReduceInventoryProcessor reduceInventoryProcessor;


    public InventoryManager(
        UpdateInventoryProcessor updateInventoryProcessor,
        OpenInventoryProcessor openInventoryProcessor,
        CloseInventoryProcessor closeInventoryProcessor,
        IncreaseInventoryProcessor increaseInventoryProcessor,
        ReduceInventoryProcessor reduceInventoryProcessor
    ) {
        this.updateInventoryProcessor = updateInventoryProcessor;
        this.openInventoryProcessor = openInventoryProcessor;
        this.closeInventoryProcessor = closeInventoryProcessor;
        this.increaseInventoryProcessor = increaseInventoryProcessor;
        this.reduceInventoryProcessor = reduceInventoryProcessor;
    }

    public void open(OpenInventoryCommand command) {
        openInventoryProcessor.execute(command);
    }

    public void close(CloseInventoryCommand command) {
        closeInventoryProcessor.execute(command);
    }

    public void increase(IncreaseInventoryCommand command) {
        increaseInventoryProcessor.execute(command);
    }

    public void reduce(ReduceInventoryCommand command) {
        reduceInventoryProcessor.execute(command);
    }

    public Inventory update(UpdateInventoryCommand command) {
        updateInventoryProcessor.execute(command);
        return null;
    }
}
