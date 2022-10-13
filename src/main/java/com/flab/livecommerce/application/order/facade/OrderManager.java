package com.flab.livecommerce.application.order.facade;

import com.flab.livecommerce.application.order.RegisterOrderProcessor;
import com.flab.livecommerce.application.order.command.RegisterOrderCommand;
import com.flab.livecommerce.domain.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderManager {

    private final RegisterOrderProcessor registerOrderProcessor;

    public OrderManager(
        RegisterOrderProcessor registerOrderProcessor
    ) {
        this.registerOrderProcessor = registerOrderProcessor;
    }

    public Order register(RegisterOrderCommand command) {
        return registerOrderProcessor.execute(command);
    }
}
