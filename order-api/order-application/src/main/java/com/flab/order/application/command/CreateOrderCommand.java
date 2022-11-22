package com.flab.order.application.command;

import com.flab.order.domain.OrderItemOption;
import com.flab.order.domain.OrderItemOptionGroup;
import com.flab.order.domain.OrderLineItem;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderCommand {

    private String payMethod;
    private List<CreateOrderLineItemCommand> orderLineItems;

    public CreateOrderCommand(
        String payMethod,
        List<CreateOrderLineItemCommand> orderLineItems
    ) {
        this.payMethod = payMethod;
        this.orderLineItems = orderLineItems;
    }

    public List<OrderLineItem> toLineItems() {
        return this.orderLineItems.stream().map(
            lineItemCommand -> new OrderLineItem(
                lineItemCommand.getOrderCount(),
                lineItemCommand.getItemId(),
                lineItemCommand.getName(),
                lineItemCommand.getPrice(),
                toItemOptionGroups(lineItemCommand)
            )
        ).collect(Collectors.toList());
    }

    private List<OrderItemOptionGroup> toItemOptionGroups(
        CreateOrderLineItemCommand lineItemCommand
    ) {
        return lineItemCommand.getOrderItemOptionGroups().stream().map(
            itemOptionGroupCommand -> new OrderItemOptionGroup(
                itemOptionGroupCommand.getOrdering(),
                itemOptionGroupCommand.getName(),
                toItemOption(itemOptionGroupCommand)
            )
        ).collect(Collectors.toList());
    }

    private List<OrderItemOption> toItemOption(
        CreateOrderItemOptionGroupCommand itemOptionGroupCommand
    ) {
        return itemOptionGroupCommand.getOrderItemOptions().stream().map(
            itemOptionCommand -> new OrderItemOption(
                itemOptionCommand.getOrdering(),
                itemOptionCommand.getName(),
                itemOptionCommand.getPrice()
            )
        ).collect(Collectors.toList());
    }

    public String getPayMethod() {
        return payMethod;
    }

    public List<CreateOrderLineItemCommand> getOrderLineItems() {
        return orderLineItems;
    }
}
