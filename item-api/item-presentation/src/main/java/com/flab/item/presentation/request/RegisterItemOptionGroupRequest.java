package com.flab.item.presentation.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterItemOptionGroupRequest {

    @NotNull(message = "ordering 을 작성하세요.")
    private Integer ordering;

    @NotBlank(message = "상품 옵션 그룹 이름을 작성하세요.")
    private String name;

    @Valid
    private List<RegisterItemOptionRequest> itemOptions;

    public RegisterItemOptionGroupRequest(
        Integer ordering,
        String name,
        List<RegisterItemOptionRequest> itemOptions
    ) {
        this.ordering = ordering;
        this.name = name;
        this.itemOptions = itemOptions;
    }

    private RegisterItemOptionGroupRequest() {
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }

    public List<RegisterItemOptionRequest> getItemOptions() {
        return itemOptions;
    }
}
