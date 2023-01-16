package com.flab.inventory.application;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.domain.ItemQuantity;
import java.util.List;

public interface DecreaseInventoryProcessor {
    List<ItemQuantity> execute(DecreaseInventoryCommand command);
}
