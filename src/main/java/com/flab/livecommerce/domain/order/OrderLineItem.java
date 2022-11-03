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
public class OrderLineItem {

    private Long id;
    private Long orderId;
    private Integer orderCount;
    private Long sellerId;
    private Long itemId;
    private String name;
    private Long price;
    private List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();

    @Builder
    public OrderLineItem(
        Long orderId,
        Integer orderCount,
        Long sellerId,
        Long itemId,
        String name,
        Long price,
        List<OrderItemOptionGroup> orderItemOptionGroups
    ) {
        if (orderCount == null) {
            throw new InvalidParameterException("OrderLineItem.orderCount");
        }
        if (itemId == null) {
            throw new InvalidParameterException("OrderLineItem.itemId");
        }
        if (name == null && name.length() == 0) {
            throw new InvalidParameterException("OrderLineItem.itemName");
        }
        if (price == null) {
            throw new InvalidParameterException("OrderLineItem.itemPrice");
        }

        this.orderId = orderId;
        this.orderCount = orderCount;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.orderItemOptionGroups = orderItemOptionGroups;
    }

    public OrderLineItem setId(Long id) {
        this.id = id;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
