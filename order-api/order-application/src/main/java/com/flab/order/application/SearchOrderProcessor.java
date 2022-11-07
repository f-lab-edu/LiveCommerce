package com.flab.order.application;

import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;

public class SearchOrderProcessor {

    private final OrderRepository orderRepository;

    public SearchOrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id) {
        return orderRepository.findById(id);
    }
}
