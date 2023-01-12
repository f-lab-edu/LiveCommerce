package com.flab.item.presentation.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class RegisterItemOptionRequest {

    @NotNull(message = "ordering 을 작성하세요.")
    private Integer ordering;

    @NotBlank(message = "상품 옵션 이름을 작성하세요.")
    private String name;

    @NotNull(message = "상품 옵션 가격을 입력하세요.")
    @Range(min = 0, max = 200000000, message = "상품 옵션 가격은 0 ~ 2억 미만이여야 합니다.")
    private Integer price;

    public RegisterItemOptionRequest(Integer ordering, String name, Integer price) {
        this.ordering = ordering;
        this.name = name;
        this.price = price;
    }

    private RegisterItemOptionRequest() {
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
