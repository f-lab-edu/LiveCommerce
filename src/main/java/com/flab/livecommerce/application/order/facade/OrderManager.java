package com.flab.livecommerce.application.order.facade;

import com.flab.livecommerce.application.order.RegisterOrderProcessor;
import com.flab.livecommerce.application.order.SearchOrderProcessor;
import com.flab.livecommerce.application.order.command.RegisterOrderCommand;
import com.flab.livecommerce.domain.order.Order;
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
