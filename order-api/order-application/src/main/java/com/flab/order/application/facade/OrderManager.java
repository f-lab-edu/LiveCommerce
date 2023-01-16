package com.flab.order.application.facade;

import com.flab.order.application.CompletedProcessor;
import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.FailInventoryReducedProcessor;
import com.flab.order.application.PaymentCompletedProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.FailInventoryReducedCommand;
import com.flab.order.application.command.PaymentCompletedCommand;
import com.flab.order.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final CreateOrderProcessor createOrderProcessor;
    private final SearchOrderProcessor searchOrderProcessor;
    private final PaymentCompletedProcessor paymentCompletedProcessor;
    private final FailInventoryReducedProcessor failInventoryReducedProcessor;
    private final CompletedProcessor completedProcessor;

    public OrderManager(
        CreateOrderProcessor createOrderProcessor,
        SearchOrderProcessor searchOrderProcessor,
        PaymentCompletedProcessor paymentCompletedProcessor,
        FailInventoryReducedProcessor failInventoryReducedProcessor,
        CompletedProcessor completedProcessor
    ) {
        this.createOrderProcessor = createOrderProcessor;
        this.searchOrderProcessor = searchOrderProcessor;
        this.paymentCompletedProcessor = paymentCompletedProcessor;
        this.failInventoryReducedProcessor = failInventoryReducedProcessor;
        this.completedProcessor = completedProcessor;
    }

    public Order create(Long userId, CreateOrderCommand command) {
        var order = createOrderProcessor.execute(userId, command);

        return order;
    }

    public Order search(Long id) {
        return searchOrderProcessor.execute(id);
    }

    public void payed(PaymentCompletedCommand command) {
        paymentCompletedProcessor.execute(command);
    }

    public void fail(FailInventoryReducedCommand command) {
        failInventoryReducedProcessor.execute(command);
    }

    public void completed(Long orderId) {
        completedProcessor.execute(orderId);
    }
}
