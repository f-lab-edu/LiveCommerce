package com.flab.order.presentation.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateOrderItemOptionGroupRequest {

    @NotNull(message = "ordering 을 작성하세요.")
    private Integer ordering;

    @NotBlank(message = "name 을 작성하세요.")
    private String name;

    @Valid
    private List<CreateOrderItemOptionRequest> orderItemOptions;

    protected CreateOrderItemOptionGroupRequest() {
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }

    public List<CreateOrderItemOptionRequest> getOrderItemOptions() {
        return orderItemOptions;
    }
}
