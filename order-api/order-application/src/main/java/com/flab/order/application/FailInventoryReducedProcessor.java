package com.flab.order.application;

import com.flab.order.application.command.FailInventoryReducedCommand;
import com.flab.order.domain.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

public class FailInventoryReducedProcessor {

    private final OrderRepository orderRepository;

    public FailInventoryReducedProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void execute(FailInventoryReducedCommand command) {
        var order = orderRepository.findById(command.getOrderId());
        order.cancel();
    }
}
