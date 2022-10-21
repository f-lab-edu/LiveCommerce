package com.flab.livecommerce.application.order;

import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderRepository;

public class SearchOrderProcessor {

    private final OrderRepository orderRepository;

    public SearchOrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Long id) {
        return orderRepository.findById(id);
    }
}
