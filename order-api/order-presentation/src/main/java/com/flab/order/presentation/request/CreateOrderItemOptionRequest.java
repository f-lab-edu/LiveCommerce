package com.flab.order.presentation.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class CreateOrderItemOptionRequest {

    @NotNull(message = "ordering 을 작성하세요.")
    private Integer ordering;

    @NotBlank(message = "name 을 작성하세요.")
    private String name;

    @NotNull(message = "price 를 작성하세요.")
    private Integer price;

    private CreateOrderItemOptionRequest() {
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}

