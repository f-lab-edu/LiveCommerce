package com.flab.order.application;

import com.flab.order.application.command.OrderPayedCommand;
import com.flab.order.application.result.OrderPayedResult;
import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class OrderPayedProcessor {

    private final DecreaseInventoryService decreaseInventoryService;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(OrderPayedProcessor.class);

    public OrderPayedProcessor(
        DecreaseInventoryService decreaseInventoryService,
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        this.decreaseInventoryService = decreaseInventoryService;
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Transactional
    public OrderPayedResult execute(OrderPayedCommand command) {
        var order = orderRepository.findById(command.getOrderId());
        var data = decreaseInventoryService.decreaseInventory(command.getItemQuantityData());

        if (data.getInventoryData().isEmpty()) {
            order.cancel();
            order.pollAllEvents().forEach(publisher::publishEvent);
            return new OrderPayedResult(order.getId(), data.getInventoryData());
        }

        order.completed();
        order.pollAllEvents().forEach(publisher::publishEvent);
        return new OrderPayedResult(order.getId(), data.getInventoryData());
    }
}
