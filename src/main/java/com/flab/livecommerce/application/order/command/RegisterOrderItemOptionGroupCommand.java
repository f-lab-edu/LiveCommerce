package com.flab.livecommerce.application.order.command;

import com.flab.livecommerce.domain.order.OrderItemOption;
import com.flab.livecommerce.domain.order.OrderItemOptionGroup;
import com.flab.livecommerce.domain.order.OrderLineItem;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<OrderItemOption> toItemOption() {
        return this.orderItemOptions.stream().map(
            itemOption -> OrderItemOption.builder()
                .ordering(itemOption.getOrdering())
                .name(itemOption.getName())
                .price(itemOption.getPrice())
                .build()
        ).collect(Collectors.toList());
    }
}
