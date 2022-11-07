package com.flab.livecommerce.order.domain;

import com.flab.common.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer orderCount;
    private Long sellerId;
    private Long itemId;
    private String name;
    private Long price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_line_item_id")
    private List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();

    @Builder
    public OrderLineItem(
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

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
