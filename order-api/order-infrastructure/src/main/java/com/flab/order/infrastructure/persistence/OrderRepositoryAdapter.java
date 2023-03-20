package com.flab.order.infrastructure.persistence;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.common.exception.ErrorCode;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import com.flab.order.infrastructure.persistence.jpa.JpaOrderRepository;

public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository orderRepository;

    public OrderRepositoryAdapter(JpaOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ORDER_NOT_FOUND));
    }
}
