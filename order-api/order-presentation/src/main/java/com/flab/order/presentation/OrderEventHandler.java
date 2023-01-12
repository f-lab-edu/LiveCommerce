package com.flab.order.presentation;

import com.flab.order.application.facade.OrderManager;
import com.flab.order.presentation.request.FailInventoryReducedEvent;
import com.flab.order.presentation.request.PaymentCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    private final OrderManager orderManager;

    public OrderEventHandler(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @EventListener
    public void handle(PaymentCompletedEvent event) {
        orderManager.payed(event.toCommand());
    }

    @EventListener
    public void handle(FailInventoryReducedEvent event) {
        orderManager.fail(event.toCommand());
    }
}
