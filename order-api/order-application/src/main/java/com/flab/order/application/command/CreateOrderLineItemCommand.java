package com.flab.order.application.command;

import java.util.List;

public final class CreateOrderLineItemCommand {

    private Integer orderCount;
    private Long itemId;
    private String name;
    private Integer price;
    private List<CreateOrderItemOptionGroupCommand> orderItemOptionGroups;

    public CreateOrderLineItemCommand(
        Integer orderCount,
        Long itemId,
        String name,
        Integer price,
        List<CreateOrderItemOptionGroupCommand> orderItemOptionGroups
    ) {
        this.orderCount = orderCount;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.orderItemOptionGroups = orderItemOptionGroups;
    }

    private CreateOrderLineItemCommand() {
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public List<CreateOrderItemOptionGroupCommand> getOrderItemOptionGroups() {
        return orderItemOptionGroups;
    }
}
