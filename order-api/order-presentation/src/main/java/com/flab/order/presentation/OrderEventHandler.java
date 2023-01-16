package com.flab.order.presentation;

import com.flab.order.application.facade.OrderManager;
import com.flab.order.domain.event.OrderPayedEvent;
import com.flab.order.presentation.request.PaymentCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEventHandler {

    private final OrderManager orderManager;
    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    public OrderEventHandler(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @EventListener
    public void handle(PaymentCompletedEvent event) {
        orderManager.payed(event.toCommand());
    }

    @Async
    @TransactionalEventListener
    public void orchestrate(OrderPayedEvent event) {
        log.info(">>>>> 비동기 작업 수행");
        orderManager.completed(event.getOrderId());
    }
}
