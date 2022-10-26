package com.flab.livecommerce.infrastructure.order.persistence;

import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderItemSeriesService;
import com.flab.livecommerce.domain.order.OrderLineItem;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderItemOptionGroupMapper;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderItemOptionMapper;
import com.flab.livecommerce.infrastructure.order.persistence.mybatis.MyBatisOrderLineItemMapper;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemSeriesServiceImpl implements OrderItemSeriesService {

    private final ItemRepository itemRepository;
    private final MyBatisOrderLineItemMapper orderLineItemMapper;
    private final MyBatisOrderItemOptionGroupMapper orderItemOptionGroupMapper;
    private final MyBatisOrderItemOptionMapper orderItemOptionMapper;

    public OrderItemSeriesServiceImpl(
        ItemRepository itemRepository,
        MyBatisOrderLineItemMapper orderLineItemMapper,
        MyBatisOrderItemOptionGroupMapper orderItemOptionGroupMapper,
        MyBatisOrderItemOptionMapper orderItemOptionMapper
    ) {
        this.itemRepository = itemRepository;
        this.orderLineItemMapper = orderLineItemMapper;
        this.orderItemOptionGroupMapper = orderItemOptionGroupMapper;
        this.orderItemOptionMapper = orderItemOptionMapper;
    }

    @Override
    public List<OrderLineItem> save(Order order) {
        var orderLineItems = order.getOrderLineItems();

        if (orderLineItems.isEmpty()) {
            return Collections.emptyList();
        }

        return orderLineItems.stream().map(
            orderLineItem -> {
                var item = itemRepository.findById(orderLineItem.getItemId());
                orderLineItem.setOrderId(order.getId());
                orderLineItem.setShopId(item.getShopId());
                orderLineItemMapper.save(orderLineItem);

                orderLineItem.getOrderItemOptionGroups().forEach(
                    orderItemOptionGroup -> {
                        orderItemOptionGroup.setOrderLineItemId(orderLineItem.getId());
                        orderItemOptionGroupMapper.save(orderItemOptionGroup);

                        orderItemOptionGroup.getOrderItemOptions().forEach(
                            orderItemOption -> {
                                orderItemOption.setOrderItemOptionGroupId(orderItemOptionGroup.getId());
                                orderItemOptionMapper.save(orderItemOption);
                            }
                        );
                    }
                );
                return orderLineItem;
            }
        ).collect(Collectors.toList());
    }
}

