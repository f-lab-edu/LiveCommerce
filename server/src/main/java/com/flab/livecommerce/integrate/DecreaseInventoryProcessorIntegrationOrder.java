package com.flab.livecommerce.integrate;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.order.domain.DecreaseInventoryProcessor;
import com.flab.order.domain.ItemQuantity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public final class DecreaseInventoryProcessorIntegrationOrder implements DecreaseInventoryProcessor {

    private final com.flab.inventory.application.DecreaseInventoryProcessor decreaseInventoryProcessor;

    public DecreaseInventoryProcessorIntegrationOrder(
        com.flab.inventory.application.DecreaseInventoryProcessor decreaseInventoryProcessor
    ) {
        this.decreaseInventoryProcessor = decreaseInventoryProcessor;
    }

    @Override
    public List<ItemQuantity> execute(List<ItemQuantity> itemQuantities) {
        List<com.flab.inventory.domain.ItemQuantity> result = decreaseInventoryProcessor.execute(
            new DecreaseInventoryCommand(itemQuantities.stream()
                .map(item -> new com.flab.inventory.domain.ItemQuantity(
                    item.getItemId(),
                    item.getCount()))
                .collect(Collectors.toList())));

        return result.stream()
            .map(item -> new ItemQuantity(item.getItemId(), item.getCount()))
            .collect(Collectors.toList());
    }
}
