package com.flab.livecommerce.order.application;

import com.flab.livecommerce.order.domain.Order;
import com.flab.livecommerce.order.domain.OrderRepository;

public class SearchOrderProcessor {

    private final OrderRepository orderRepository;

    public SearchOrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id) {
        return orderRepository.findById(id);
    }
}
