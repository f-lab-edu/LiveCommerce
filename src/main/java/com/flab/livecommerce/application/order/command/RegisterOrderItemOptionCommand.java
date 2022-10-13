package com.flab.livecommerce.application.order.command;

import com.flab.livecommerce.domain.order.OrderItemOption;
import com.flab.livecommerce.domain.order.OrderItemOptionGroup;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOrderItemOptionCommand {

    private Integer ordering;
    private String name;
    private Long price;

    public OrderItemOption toEntity(OrderItemOptionGroup orderItemOptionGroup) {
        return OrderItemOption.builder()
            .orderItemOptionGroupId(orderItemOptionGroup.getId())
            .ordering(ordering)
            .name(name)
            .price(price)
            .build();
    }
}
