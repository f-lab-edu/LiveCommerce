package com.flab.livecommerce.order.infrastructure.persistence;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.order.domain.Order;
import com.flab.livecommerce.order.domain.OrderRepository;
import com.flab.livecommerce.order.infrastructure.persistence.jpa.JpaOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository orderRepository;

    public OrderRepositoryAdapter(
        JpaOrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        log.info(">>>>> order = {}", order.toString());
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public Order findById(Long id) {
        Order order = this.orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());

        return order;
    }
}
