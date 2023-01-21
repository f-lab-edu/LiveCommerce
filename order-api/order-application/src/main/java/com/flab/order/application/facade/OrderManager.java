package com.flab.order.application.facade;

import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.OrderPayedProcessor;
import com.flab.order.application.PaymentCompletedProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.PaymentCompletedCommand;
import com.flab.order.domain.Order;
import com.flab.order.domain.event.OrderPayedEvent;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final CreateOrderProcessor createOrderProcessor;
    private final SearchOrderProcessor searchOrderProcessor;
    private final PaymentCompletedProcessor paymentCompletedProcessor;
    private final OrderPayedProcessor orderPayedProcessor;

    public OrderManager(
        CreateOrderProcessor createOrderProcessor,
        SearchOrderProcessor searchOrderProcessor,
        PaymentCompletedProcessor paymentCompletedProcessor,
        OrderPayedProcessor orderPayedProcessor
    ) {
        this.createOrderProcessor = createOrderProcessor;
        this.searchOrderProcessor = searchOrderProcessor;
        this.paymentCompletedProcessor = paymentCompletedProcessor;
        this.orderPayedProcessor = orderPayedProcessor;
    }

    public Order create(Long userId, CreateOrderCommand command) {
        var order = createOrderProcessor.execute(userId, command);

        return order;
    }

    public Order search(Long id) {
        return searchOrderProcessor.execute(id);
    }

    public void paymentCompleted(PaymentCompletedCommand command) {
        paymentCompletedProcessor.execute(command);
    }

    public void orderPayed(OrderPayedEvent event) {
        orderPayedProcessor.execute(event);
    }
}
