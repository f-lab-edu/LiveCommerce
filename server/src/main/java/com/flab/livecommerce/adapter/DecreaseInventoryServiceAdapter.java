package com.flab.livecommerce.adapter;

import com.flab.common.exception.BaseException;
import com.flab.inventory.application.DecreaseInventoryProcessor;
import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.application.result.InventoryResult;
import com.flab.inventory.domain.data.ItemQuantity;
import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.data.DecreaseInventoryData;
import com.flab.order.domain.event.OrderPayedEvent;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecreaseInventoryServiceAdapter implements DecreaseInventoryService {

    private final DecreaseInventoryProcessor decreaseInventoryProcessor;
    private final Logger log = LoggerFactory.getLogger(DecreaseInventoryServiceAdapter.class);

    public DecreaseInventoryServiceAdapter(DecreaseInventoryProcessor decreaseInventoryProcessor) {
        this.decreaseInventoryProcessor = decreaseInventoryProcessor;
    }

    @Override
    public DecreaseInventoryData service(OrderPayedEvent event) {
        try {
            InventoryResult result = decreaseInventoryProcessor.execute(
                new DecreaseInventoryCommand(event.getItemQuantities().stream()
                    .map(item -> new ItemQuantity(item.getItemId(), item.getCount()))
                    .collect(Collectors.toList())));

            return DecreaseInventoryData.success(result.getInventoryData().stream()
                .map(inventory -> new com.flab.order.domain.data.InventoryData(
                    inventory.getInventoryId(), inventory.getQuantity()))
                .collect(Collectors.toList()));
        } catch (BaseException e) {
            log.error("error =", e);
            return DecreaseInventoryData.fail(null);
        }
    }
}
