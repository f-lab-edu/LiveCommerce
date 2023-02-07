package com.flab.order.application;

import com.flab.order.application.result.OrderResult;
import com.flab.order.domain.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

public class SearchOrderProcessor {

    private final OrderRepository orderRepository;

    public SearchOrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderResult execute(Long id) {
        var order = orderRepository.findById(id);

        return new OrderResult(order.getId(), order.getUserId(), order.getTotalAmount());
    }
}
