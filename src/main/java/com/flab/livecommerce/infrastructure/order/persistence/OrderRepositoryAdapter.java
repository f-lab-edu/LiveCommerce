package com.flab.livecommerce.infrastructure.order.persistence;

import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemOption;
import com.flab.livecommerce.domain.order.OrderItemOptionGroup;
import com.flab.livecommerce.domain.order.OrderLineItem;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.jdbctemplate.JdbcTemplateOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final JdbcTemplateOrderRepository orderRepository;

    public OrderRepositoryAdapter(JdbcTemplateOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public OrderLineItem save(OrderLineItem orderLineItem) {
        return null;
    }

    @Override
    public OrderItemOptionGroup save(OrderItemOptionGroup orderItemOption) {
        return null;
    }

    @Override
    public OrderItemOption save(OrderItemOption orderItemOption) {
        return null;
    }
}
