package com.flab.order.application;

import com.flab.order.domain.DecreaseInventoryProcessor;
import com.flab.order.domain.ItemQuantity;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.event.OrderPayedEvent;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

public class CompletedProcessor {

    private final OrderRepository orderRepository;
    private final DecreaseInventoryProcessor decreaseInventoryProcessor;
    private final ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(CompletedProcessor.class);

    public CompletedProcessor(
        OrderRepository orderRepository,
        DecreaseInventoryProcessor decreaseInventoryProcessor,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.decreaseInventoryProcessor = decreaseInventoryProcessor;
        this.publisher = publisher;
    }

    @Transactional
    public void execute(OrderPayedEvent event) {
        var itemQuantities = event.getItemQuantities();

        List<ItemQuantity> result = decreaseInventoryProcessor.execute(itemQuantities);
        var order = orderRepository.findById(event.getOrderId());
        //if(result.valid()) = false => order.cancel(); return

        order.completed();
        log.info("OrderComplete success");
        //return result
    }
}
