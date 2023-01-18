package com.flab.order.application;

import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.ItemQuantity;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.event.OrderPayedEvent;
import java.util.List;
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
    public void execute(OrderPayedEvent event) {
        log.info(">>>>재고 감소 Service 호출");
        List<ItemQuantity> result = decreaseInventoryService.service(event);

        log.info(">>>>재고 감소 api 호출 완료");
        var order = orderRepository.findById(event.getOrderId());

        order.completed();
    }
}
