package com.flab.order.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private Integer orderCount;
    private String name;
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_line_item_id")
    private List<OrderItemOptionGroup> orderItemOptionGroups = new ArrayList<>();

    protected OrderLineItem() {
    }

    public OrderLineItem(
        Integer orderCount,
        Long itemId,
        String name,
        Integer price,
        List<OrderItemOptionGroup> orderItemOptionGroups
    ) {
        this.orderCount = orderCount;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.orderItemOptionGroups = orderItemOptionGroups;
    }

    public Integer calculateTotalAmount() {
        var itemOptionTotalAmount = orderItemOptionGroups.stream()
            .mapToInt(OrderItemOptionGroup::calculateTotalAmount)
            .sum();

        return (this.price + itemOptionTotalAmount) * this.orderCount;
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public List<OrderItemOptionGroup> getOrderItemOptionGroups() {
        return orderItemOptionGroups;
    }
}
