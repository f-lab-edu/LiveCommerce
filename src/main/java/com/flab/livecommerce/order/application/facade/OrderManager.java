package com.flab.livecommerce.order.application.facade;

import com.flab.livecommerce.order.application.RegisterOrderProcessor;
import com.flab.livecommerce.order.application.SearchOrderProcessor;
import com.flab.livecommerce.order.application.command.RegisterOrderCommand;
import com.flab.livecommerce.order.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final RegisterOrderProcessor registerOrderProcessor;
    private final SearchOrderProcessor searchOrderProcessor;

    public OrderManager(
        RegisterOrderProcessor registerOrderProcessor,
        SearchOrderProcessor searchOrderProcessor
    ) {
        this.registerOrderProcessor = registerOrderProcessor;
        this.searchOrderProcessor = searchOrderProcessor;
    }

    public Order register(RegisterOrderCommand command) {
        return registerOrderProcessor.execute(command);
    }

    public Order search(Long id) {
        return searchOrderProcessor.execute(id);
    }
}
