package com.flab.order.application;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeOrderRepository implements OrderRepository {

    private final Map<Long, Order> data = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Order save(Order order) {
        order.setId(idGenerator.incrementAndGet());
        return data.put(order.getId(), order);
    }

    @Override
    public Order findById(Long id) {
        return data.values().stream()
            .filter(order -> order.getId().equals(id))
            .findFirst()
            .orElseThrow(EntityNotFoundException::new);
    }
}