package com.flab.order.application;

import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.data.DecreaseInventoryData;
import com.flab.order.domain.event.OrderPayedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class OrderPayedProcessor {

    private final DecreaseInventoryService decreaseInventoryService;
    private final OrderRepository orderRepository;
    private final Logger log = LoggerFactory.getLogger(OrderPayedProcessor.class);

    public OrderPayedProcessor(
        DecreaseInventoryService decreaseInventoryService,
        OrderRepository orderRepository
    ) {
        this.decreaseInventoryService = decreaseInventoryService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Object execute(OrderPayedEvent event) {
        var order = orderRepository.findById(event.getOrderId());

        DecreaseInventoryData data = decreaseInventoryService.service(event);

        //todo null 말고 적절하게 반환할 수 있도록 수정하기
        if (!data.isSuccess()) {
            order.cancel();
            return null;
        }

        order.completed();
        return null;
    }
}
