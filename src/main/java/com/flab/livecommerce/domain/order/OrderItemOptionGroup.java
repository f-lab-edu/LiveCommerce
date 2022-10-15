package com.flab.livecommerce.domain.order;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemOptionGroup {

    private Long id;
    private Long orderLineItemId;
    private Integer ordering;
    private String name;
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    @Builder
    public OrderItemOptionGroup(
        Long orderLineItemId,
        Integer ordering,
        String name,
        List<OrderItemOption> orderItemOptions
    ) {
        if (orderLineItemId == null) {
            throw new InvalidParameterException("OrderItemOptionGroup.orderLineItemId");
        }
        if (ordering == null) {
            throw new InvalidParameterException("OrderItemOptionGroup.ordering");
        }
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("OrderItemOptionGroup.name");
        }

        this.orderLineItemId = orderLineItemId;
        this.ordering = ordering;
        this.name = name;
        this.orderItemOptions = orderItemOptions;
    }

    public OrderItemOptionGroup setId(Long id) {
        this.id = id;
        return this;
    }
}
