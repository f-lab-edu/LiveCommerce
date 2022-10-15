package com.flab.livecommerce.application.order.command;

import com.flab.livecommerce.domain.order.OrderItemOptionGroup;
import com.flab.livecommerce.domain.order.OrderLineItem;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOrderItemOptionGroupCommand {

    private Integer ordering;
    private String name;
    private List<RegisterOrderItemOptionCommand> orderItemOptions;

    public OrderItemOptionGroup toEntity(OrderLineItem orderLineItem) {

        return OrderItemOptionGroup.builder()
            .orderLineItemId(orderLineItem.getId())
            .ordering(ordering)
            .name(name)
            .build();
    }
}
