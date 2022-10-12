package com.flab.livecommerce.application.order;

import com.flab.livecommerce.application.order.command.RegisterOrderCommand;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

public class RegisterOrderProcessor {
    private final OrderRepository orderRepository;
    private final OrderItemSeriesService orderItemSeriesService;

    public RegisterOrderProcessor(
        OrderRepository orderRepository,
        OrderItemSeriesService orderItemSeriesService
    ) {
        this.orderRepository = orderRepository;
        this.orderItemSeriesService = orderItemSeriesService;
    }

    @Transactional
    public Order execute(RegisterOrderCommand command) {
        var order = orderRepository.save(command.toEntity());
        orderItemSeriesService.save(command, order);

        return order;
    }
}
