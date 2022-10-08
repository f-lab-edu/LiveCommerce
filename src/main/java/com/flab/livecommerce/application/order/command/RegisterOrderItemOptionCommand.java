package com.flab.livecommerce.application.order.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOrderItemOptionCommand {

    private Integer ordering;
    private String name;
    private Long price;
}
