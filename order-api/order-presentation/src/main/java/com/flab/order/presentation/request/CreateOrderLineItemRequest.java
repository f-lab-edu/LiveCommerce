package com.flab.order.presentation.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public final class CreateOrderLineItemRequest {

    @NotNull(message = "orderCount 를 작성하세요.")
    private Integer orderCount;

    @NotNull(message = "itemId 를 작성하세요.")
    private Long itemId;

    @NotBlank(message = "name 을 작성하세요.")
    private String name;

    @NotNull(message = "price 를 작성하세요.")
    private Integer price;

    @Valid
    private List<CreateOrderItemOptionGroupRequest> orderItemOptionGroups;

    private CreateOrderLineItemRequest() {
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

    public List<CreateOrderItemOptionGroupRequest> getOrderItemOptionGroups() {
        return orderItemOptionGroups;
    }
}
