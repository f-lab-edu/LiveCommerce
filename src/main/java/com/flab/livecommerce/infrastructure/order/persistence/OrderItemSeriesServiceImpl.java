package com.flab.livecommerce.infrastructure.order.persistence;

import com.flab.livecommerce.application.order.command.RegisterOrderCommand;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderLineItem;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemSeriesServiceImpl implements OrderItemSeriesService {

    private final ItemRepository itemRepository;
    private final OrderRepositoryAdapter orderRepository;

    public OrderItemSeriesServiceImpl(
        ItemRepository itemRepository,
        OrderRepositoryAdapter orderRepositoryAdapter
    ) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepositoryAdapter;
    }

    @Override
    public List<OrderLineItem> save(RegisterOrderCommand command, Order order) {
        var orderLineItems = command.getOrderLineItems();

        if (orderLineItems.isEmpty()) {
            return Collections.emptyList();
        }

        return orderLineItems.stream().map(
            orderLineItemCommand -> {
                var item = itemRepository.findById(orderLineItemCommand.getItemId());
                var initOrderLineItem = orderLineItemCommand.toEntity(order, item);
                var orderLineItem = orderRepository.save(initOrderLineItem);

                orderLineItemCommand.getOrderItemOptionGroups().forEach(
                    orderItemOptionGroupCommand -> {
                        var initOrderItemOptionGroup = orderItemOptionGroupCommand
                            .toEntity(orderLineItem);
                        var orderItemOptionGroup = orderRepository
                            .save(initOrderItemOptionGroup);

                        orderItemOptionGroupCommand.getOrderItemOptions().forEach(
                            orderItemOptionCommand -> {
                                var initOrderItemOption = orderItemOptionCommand
                                    .toEntity(orderItemOptionGroup);
                                orderRepository.save(initOrderItemOption);
                            }
                        );
                    }
                );
                return orderLineItem;
            }
        ).collect(Collectors.toList());
    }
}

