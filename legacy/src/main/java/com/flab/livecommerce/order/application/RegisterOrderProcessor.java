package com.flab.livecommerce.order.application;

import com.flab.livecommerce.order.application.command.RegisterOrderCommand;
import com.flab.livecommerce.order.domain.Order;
import com.flab.livecommerce.order.domain.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

public class RegisterOrderProcessor {
    private final OrderRepository orderRepository;

    public RegisterOrderProcessor(
        OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order execute(RegisterOrderCommand command) {
        var order = orderRepository.save(command.toEntity());

        return order;
    }
}
