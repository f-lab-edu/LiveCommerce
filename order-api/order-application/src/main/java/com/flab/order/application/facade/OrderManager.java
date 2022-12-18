package com.flab.order.application.facade;

import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.domain.Order;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final CreateOrderProcessor createOrderProcessor;
    private final SearchOrderProcessor searchOrderProcessor;
    private final ApplicationEventPublisher publisher;

    public OrderManager(
        CreateOrderProcessor createOrderProcessor,
        SearchOrderProcessor searchOrderProcessor,
        ApplicationEventPublisher publisher
    ) {
        this.createOrderProcessor = createOrderProcessor;
        this.searchOrderProcessor = searchOrderProcessor;
        this.publisher = publisher;
    }

    public Order create(Long userId, CreateOrderCommand command) {
        var order = createOrderProcessor.execute(userId, command);
        order.pollAllEvents().forEach(publisher::publishEvent);
        return order;
    }

    public Order search(Long id) {
        return searchOrderProcessor.execute(id);
    }
}
