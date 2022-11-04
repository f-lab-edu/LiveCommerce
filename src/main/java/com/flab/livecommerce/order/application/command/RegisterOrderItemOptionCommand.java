package com.flab.livecommerce.order.application.command;

import com.flab.livecommerce.order.domain.OrderItemOption;
import com.flab.livecommerce.order.domain.OrderItemOptionGroup;
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
            .ordering(ordering)
            .name(name)
            .price(price)
            .build();
    }
}
