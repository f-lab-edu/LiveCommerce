package com.flab.livecommerce.order.domain;

public interface OrderRepository {

    Order save(Order order);

    Order findById(Long id);
}
