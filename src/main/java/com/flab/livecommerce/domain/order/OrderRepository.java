package com.flab.livecommerce.domain.order;

public interface OrderRepository {

    Order save(Order order);

    OrderLineItem save(OrderLineItem orderLineItem);

    OrderItemOptionGroup save(OrderItemOptionGroup orderItemOptionGroup);

    OrderItemOption save(OrderItemOption orderItemOption);
}
