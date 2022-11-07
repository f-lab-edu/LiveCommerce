package com.flab.order.application.facade;

import com.flab.order.application.CreateOrderProcessor;
import com.flab.order.application.SearchOrderProcessor;
import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final CreateOrderProcessor createOrderProcessor;
    private final SearchOrderProcessor searchOrderProcessor;

    public OrderManager(
        CreateOrderProcessor createOrderProcessor,
        SearchOrderProcessor searchOrderProcessor
    ) {
        this.createOrderProcessor = createOrderProcessor;
        this.searchOrderProcessor = searchOrderProcessor;
    }

    public Order create(Long userId, CreateOrderCommand command) {
        return createOrderProcessor.execute(userId, command);
    }

    public Order search(Long id) {
        return searchOrderProcessor.execute(id);
    }
}
