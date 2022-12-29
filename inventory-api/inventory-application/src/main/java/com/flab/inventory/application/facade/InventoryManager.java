package com.flab.inventory.application.facade;

import com.flab.inventory.application.CloseInventoryProcessor;
import com.flab.inventory.application.IncreaseInventoryProcessor;
import com.flab.inventory.application.OpenInventoryProcessor;
import com.flab.inventory.application.ReduceInventoryProcessor;
import com.flab.inventory.application.command.CloseInventoryCommand;
import com.flab.inventory.application.command.IncreaseInventoryCommand;
import com.flab.inventory.application.command.OpenInventoryCommand;
import com.flab.inventory.application.command.ReduceInventoryCommand;
import org.springframework.stereotype.Service;

@Service
public class InventoryManager {

    private final OpenInventoryProcessor openInventoryProcessor;
    private final CloseInventoryProcessor closeInventoryProcessor;
    private final IncreaseInventoryProcessor increaseInventoryProcessor;
    private final ReduceInventoryProcessor reduceInventoryProcessor;


    public InventoryManager(
        OpenInventoryProcessor openInventoryProcessor,
        CloseInventoryProcessor closeInventoryProcessor,
        IncreaseInventoryProcessor increaseInventoryProcessor,
        ReduceInventoryProcessor reduceInventoryProcessor
    ) {
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

}
