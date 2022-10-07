package com.flab.livecommerce.domain.order;

public interface OrderRepository {

    Order save(Order order);

    OrderLineItem save(OrderLineItem orderLineItem);

    OrderItemOptionGroup save(OrderItemOptionGroup orderItemOption);

    OrderItemOption save(OrderItemOption orderItemOption);
}
