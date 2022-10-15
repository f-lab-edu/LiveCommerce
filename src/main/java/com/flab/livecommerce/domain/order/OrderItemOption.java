package com.flab.livecommerce.domain.order;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemOption {

    private Long id;
    private Long orderItemOptionGroupId;
    private Integer ordering;
    private String name;
    private Long price;

    @Builder
    public OrderItemOption(
        Long orderItemOptionGroupId,
        Integer ordering,
        String name,
        Long price
    ) {
        if (orderItemOptionGroupId == null) {
            throw new InvalidParameterException("orderItemOption.orderItemOptionGroupId");
        }
        if (ordering == null) {
            throw new InvalidParameterException("orderItemOption.ordering");
        }
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("orderItemOption.name");
        }
        if (price == null) {
            throw new InvalidParameterException("orderItemOption.price");
        }

        this.orderItemOptionGroupId = orderItemOptionGroupId;
        this.ordering = ordering;
        this.name = name;
        this.price = price;
    }

    public OrderItemOption setId(Long id) {
        this.id = id;
        return this;
    }
}
