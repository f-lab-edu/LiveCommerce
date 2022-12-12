package com.flab.inventory.infrastructure;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventHandler {

    private final ApplicationEventPublisher publisher;
//    private final InventoryCheckProcessor processor;

    public InventoryEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

//    @EventListener
//    public void handle(OrderPayedEvent event) {
//        processor.execute(event);
//    }

}
