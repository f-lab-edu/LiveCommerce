package com.flab.livecommerce.infrastructure.order.persistence;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderRepository;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final MyBatisOrderMapper orderRepository;
    private final OrderItemSeriesService orderItemSeriesService;

    public OrderRepositoryAdapter(
        MyBatisOrderMapper orderRepository,
        OrderItemSeriesService orderItemSeriesService

    ) {
        this.orderRepository = orderRepository;
        this.orderItemSeriesService = orderItemSeriesService;

    }

    @Override
    public Order save(Order order) {
        this.orderRepository.save(order);
        this.orderItemSeriesService.save(order);
        return order;
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
