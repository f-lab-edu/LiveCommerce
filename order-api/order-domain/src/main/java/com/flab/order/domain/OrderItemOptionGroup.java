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
public class OrderItemOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_option_group_id")
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    protected OrderItemOptionGroup() {
    }

    public OrderItemOptionGroup(
        Integer ordering,
        String name,
        List<OrderItemOption> orderItemOptions
    ) {
        this.ordering = ordering;
        this.name = name;
        this.orderItemOptions = orderItemOptions;
    }

    public Integer calculateTotalAmount() {
        return orderItemOptions.stream()
            .mapToInt(OrderItemOption::getPrice)
            .sum();
    }

    public OrderItemOptionGroup setId(Long id) {
        this.id = id;
        return this;
    }
}
