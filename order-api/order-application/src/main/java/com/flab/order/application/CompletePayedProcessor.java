package com.flab.order.application;

import com.flab.common.exception.BaseException;
import com.flab.order.domain.OrderRepository;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

public class CompletePayedProcessor {

    private final ApplicationEventPublisher publisher;
    private final OrderRepository orderRepository;
    private static final Logger log = LoggerFactory.getLogger(CompletePayedProcessor.class);

    public CompletePayedProcessor(
        OrderRepository orderRepository,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Transactional
    public void execute(Long orderId) {
        var order = orderRepository.findById(orderId);

        try {
            order.completed();
            order.pollAllEvents().forEach(publisher::publishEvent);
            log.info("OrderComplete success");
        } catch (BaseException e) {
            order.cancel();
            log.error("OrderComplete Fail, error = ", e);
        }
    }
}
