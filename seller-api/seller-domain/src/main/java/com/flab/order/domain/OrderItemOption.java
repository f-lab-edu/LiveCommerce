package com.flab.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class OrderItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ordering;
    private String name;
    private Integer price;

    public OrderItemOption(Integer ordering, String name, Integer price) {
        this.ordering = ordering;
        this.name = name;
        this.price = price;
    }

    protected OrderItemOption() {
    }

    public Long getId() {
        return id;
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
