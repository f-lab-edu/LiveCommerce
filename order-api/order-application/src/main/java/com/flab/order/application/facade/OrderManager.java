package com.flab.order.application.facade;

import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.PaymentCompletedProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.PaymentCompletedCommand;
import com.flab.order.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final CreateOrderProcessor createOrderProcessor;
    private final SearchOrderProcessor searchOrderProcessor;
    private final PaymentCompletedProcessor paymentCompletedProcessor;

    public OrderManager(
        CreateOrderProcessor createOrderProcessor,
        SearchOrderProcessor searchOrderProcessor,
        PaymentCompletedProcessor paymentCompletedProcessor
    ) {
        this.createOrderProcessor = createOrderProcessor;
        this.searchOrderProcessor = searchOrderProcessor;
        this.paymentCompletedProcessor = paymentCompletedProcessor;
    }

    public Order create(Long userId, CreateOrderCommand command) {
        var order = createOrderProcessor.execute(userId, command);

        return order;
    }

    public Order search(Long id) {
        return searchOrderProcessor.execute(id);
    }

    public Order payed(PaymentCompletedCommand command) {
        return paymentCompletedProcessor.execute(command);
    }
}
