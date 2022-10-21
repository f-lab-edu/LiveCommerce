package com.flab.livecommerce.domain.order;

public interface OrderRepository {

    Order save(Order order);

    Order findById(Long id);
}
