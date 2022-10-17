package com.flab.livecommerce.infrastructure.order.persistence;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemOption;
import com.flab.livecommerce.domain.order.OrderItemOptionGroup;
import com.flab.livecommerce.domain.order.OrderLineItem;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderItemOptionGroupRepository;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderItemOptionRepository;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderLineItemRepository;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final MyBatisOrderMapper orderRepository;
    private final MyBatisOrderLineItemRepository orderLineItemRepository;
    private final MyBatisOrderItemOptionGroupRepository orderItemOptionGroupRepository;
    private final MyBatisOrderItemOptionRepository orderItemOptionRepository;

    public OrderRepositoryAdapter(
        MyBatisOrderMapper orderRepository,
        MyBatisOrderLineItemRepository orderLineItemRepository,
        MyBatisOrderItemOptionGroupRepository orderItemOptionGroupRepository,
        MyBatisOrderItemOptionRepository orderItemOptionRepository
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
        this.orderLineItemRepository.save(orderLineItem);
        return orderLineItem;
    }

    @Override
    public OrderItemOptionGroup save(OrderItemOptionGroup orderItemOptionGroup) {
        this.orderItemOptionGroupRepository.save(orderItemOptionGroup);
        return orderItemOptionGroup;
    }

    @Override
    public OrderItemOption save(OrderItemOption orderItemOption) {
        this.orderItemOptionRepository.save(orderItemOption);
        return orderItemOption;
    }

    @Override
    public Order findById(Long id) {
        Order order = this.orderRepository.findById(id);

        if (order == null) {
            throw new EntityNotFoundException();
        }

        return order;
    }
}
