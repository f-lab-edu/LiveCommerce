package com.flab.order.presentation.request;

import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.CreateOrderItemOptionCommand;
import com.flab.order.application.command.CreateOrderItemOptionGroupCommand;
import com.flab.order.application.command.CreateOrderLineItemCommand;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public final class CreateOrderRequest {

    @NotBlank(message = "payMethod 를 작성하세요.")
    private String payMethod;

    @Valid
    private List<CreateOrderLineItemRequest> orderLineItems;

    private CreateOrderRequest() {
    }

    public CreateOrderCommand toCommand() {
        return new CreateOrderCommand(
            payMethod,
            toLineItems()
        );
    }

    private List<CreateOrderLineItemCommand> toLineItems() {
        return this.orderLineItems.stream().map(
            lineItemRequest -> new CreateOrderLineItemCommand(
                lineItemRequest.getOrderCount(),
                lineItemRequest.getItemId(),
                lineItemRequest.getName(),
                lineItemRequest.getPrice(),
                toItemOptionGroups(lineItemRequest)
            )
        ).collect(Collectors.toList());
    }

    private List<CreateOrderItemOptionGroupCommand> toItemOptionGroups(
        CreateOrderLineItemRequest lineItemRequest
    ) {
        return lineItemRequest.getOrderItemOptionGroups().stream().map(
            itemOptionGroupRequest -> new CreateOrderItemOptionGroupCommand(
                itemOptionGroupRequest.getOrdering(),
                itemOptionGroupRequest.getName(),
                toItemOptions(itemOptionGroupRequest)
            )
        ).collect(Collectors.toList());
    }

    private List<CreateOrderItemOptionCommand> toItemOptions(
        CreateOrderItemOptionGroupRequest itemOptionGroupRequest
    ) {
        return itemOptionGroupRequest.getOrderItemOptions().stream().map(
            itemOptionRequest -> new CreateOrderItemOptionCommand(
                itemOptionRequest.getOrdering(),
                itemOptionRequest.getName(),
                itemOptionRequest.getPrice()
            )
        ).collect(Collectors.toList());
    }

    public String getPayMethod() {
        return payMethod;
    }

    public List<CreateOrderLineItemRequest> getOrderLineItems() {
        return orderLineItems;
    }
}
