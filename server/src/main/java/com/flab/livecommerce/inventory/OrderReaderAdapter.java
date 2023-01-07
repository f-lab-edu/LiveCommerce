package com.flab.livecommerce.inventory;

import com.flab.inventory.domain.Order;
import com.flab.inventory.domain.OrderLineItem;
import com.flab.inventory.domain.OrderReader;
import com.flab.order.domain.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderReaderAdapter implements OrderReader {

    private final OrderRepository orderRepository;

    public OrderReaderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(Long id) {
        var order = orderRepository.findById(id);
        List<OrderLineItem> orderLineItems = new ArrayList<>();

        order.getOrderLineItems().forEach(
            orderLineItem -> {
                orderLineItems.add(
                    new OrderLineItem(
                        orderLineItem.getId(),
                        orderLineItem.getItemId(),
                        orderLineItem.getOrderCount()
                    )
                );
            }
        );

        return new Order(order.getId(), order.getUserId(), orderLineItems);
    }
}
