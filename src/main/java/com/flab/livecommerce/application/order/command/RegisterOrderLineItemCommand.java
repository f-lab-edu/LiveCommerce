package com.flab.livecommerce.application.order.command;

import com.flab.livecommerce.application.item.command.RegisterItemOptionGroupCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.order.Order;
import com.flab.livecommerce.domain.order.OrderLineItem;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOrderLineItemCommand {

    private Integer orderCount;
    private String name;
    private Long price;
    private List<RegisterItemOptionGroupCommand> orderItemOptionGroups;

    public OrderLineItem toEntity(Order order, Item item) {
        return OrderLineItem.builder()
            .orderId(order.getId())
            .orderCount(this.orderCount)
            .shopId(item.getShopId())
            .itemId(item.getId())
            .name(this.name)
            .price(this.price)
            .build();
    }
}
