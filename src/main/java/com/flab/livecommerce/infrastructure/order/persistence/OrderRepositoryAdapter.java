package com.flab.livecommerce.infrastructure.order.persistence;

import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemOption;
import com.flab.livecommerce.domain.order.OrderItemOptionGroup;
import com.flab.livecommerce.domain.order.OrderLineItem;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.jdbctemplate.JdbcTemplateOrderItemOptionGroupRepository;
import com.flab.livecommerce.infrastructure.order.persistence.jdbctemplate.JdbcTemplateOrderItemOptionRepository;
import com.flab.livecommerce.infrastructure.order.persistence.jdbctemplate.JdbcTemplateOrderLineItemRepository;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final MyBatisOrderRepository orderRepository;

    private final JdbcTemplateOrderLineItemRepository orderLineItemRepository;
    private final JdbcTemplateOrderItemOptionGroupRepository orderItemOptionGroupRepository;
    private final JdbcTemplateOrderItemOptionRepository orderItemOptionRepository;

    public OrderRepositoryAdapter(
        MyBatisOrderRepository orderRepository,
        JdbcTemplateOrderLineItemRepository orderLineItemRepository,
        JdbcTemplateOrderItemOptionGroupRepository orderItemOptionGroupRepository,
        JdbcTemplateOrderItemOptionRepository orderItemOptionRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.orderItemOptionGroupRepository = orderItemOptionGroupRepository;
        this.orderItemOptionRepository = orderItemOptionRepository;
    }

    @Override
    public Order save(Order order) {
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public OrderLineItem save(OrderLineItem orderLineItem) {
        return this.orderLineItemRepository.save(orderLineItem);
    }

    @Override
    public OrderItemOptionGroup save(OrderItemOptionGroup orderItemOptionGroup) {
        return this.orderItemOptionGroupRepository.save(orderItemOptionGroup);
    }

    @Override
    public OrderItemOption save(OrderItemOption orderItemOption) {
        return this.orderItemOptionRepository.save(orderItemOption);
    }

}
