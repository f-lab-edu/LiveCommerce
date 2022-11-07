package com.flab.livecommerce.order.application.command;

import com.flab.livecommerce.item.domain.Item;
import com.flab.livecommerce.order.domain.Order;
import com.flab.livecommerce.order.domain.OrderItemOptionGroup;
import com.flab.livecommerce.order.domain.OrderLineItem;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOrderLineItemCommand {

    private Integer orderCount;
    private Long itemId;
    private String name;
    private Long price;
    private List<RegisterOrderItemOptionGroupCommand> orderItemOptionGroups;

    public OrderLineItem toEntity(Order order, Item item) {
        return OrderLineItem.builder()
            .orderCount(this.orderCount)
            .sellerId(item.getSellerId())
            .itemId(this.getItemId())
            .name(this.name)
            .price(this.price)
            .build();
    }

    public List<OrderItemOptionGroup> toItemOptionGroup() {
        return this.orderItemOptionGroups.stream().map(
            itemOptionGroup -> OrderItemOptionGroup.builder()
                .ordering(itemOptionGroup.getOrdering())
                .name(itemOptionGroup.getName())
                .orderItemOptions(itemOptionGroup.toItemOption())
                .build()
        ).collect(Collectors.toList());
    }
}
