package com.flab.item.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer ordering;
    private Integer price;

    protected ItemOption() {
    }

    public ItemOption(String name, Integer ordering, Integer price) {
        this.name = name;
        this.ordering = ordering;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public Integer getPrice() {
        return price;
    }
}
