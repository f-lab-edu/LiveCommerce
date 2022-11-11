package com.flab.order.application.command;

import java.util.List;

public class CreateOrderItemOptionGroupCommand {

    private Integer ordering;
    private String name;
    private List<CreateOrderItemOptionCommand> orderItemOptions;

    public CreateOrderItemOptionGroupCommand(
        Integer ordering,
        String name,
        List<CreateOrderItemOptionCommand> orderItemOptions
    ) {
        this.ordering = ordering;
        this.name = name;
        this.orderItemOptions = orderItemOptions;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }

    public List<CreateOrderItemOptionCommand> getOrderItemOptions() {
        return orderItemOptions;
    }
}
