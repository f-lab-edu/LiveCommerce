package com.flab.inventory.infrastructure;

import com.flab.inventory.application.OrderPayedProcessor;
import com.flab.inventory.domain.event.OrderPayedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventHandler {

    private final OrderPayedProcessor processor;

    public InventoryEventHandler(
        OrderPayedProcessor processor
    ) {
        this.processor = processor;
    }

    @EventListener
    public void handle(OrderPayedEvent event) {
        processor.execute(event);
    }

}
