package com.flab.livecommerce.application.order;

import com.flab.livecommerce.application.order.command.RegisterOrderCommand;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderRepository;

public class RegisterOrderProcessor {
    private final OrderRepository orderRepository;

    public RegisterOrderProcessor(
        OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    public Order execute(RegisterOrderCommand command) {
        orderRepository.save(command.toEntity());

        return null;
    }
}
