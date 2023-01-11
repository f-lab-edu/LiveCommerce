package com.flab.item.domain;

import com.flab.common.domain.AbstractAggregateRoot;
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
public class Item extends AbstractAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private String name;
    private String description;
    private Integer price;
    private Integer salesPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    protected Item() {
    }

    public Item(
        Long sellerId,
        String name,
        String description,
        Integer price,
        Integer salesPrice,
        List<ItemOptionGroup> itemOptionGroups
    ) {
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesPrice = salesPrice;
        this.itemOptionGroups = itemOptionGroups;
    }

    public static Item create(
        Long sellerId,
        String name,
        String description,
        Integer price,
        Integer salesPrice,
        List<ItemOptionGroup> itemOptionGroups
    ) {
        return new Item(sellerId, name, description, price, salesPrice, itemOptionGroups);
    }

    public Item addItemOptionGroup(ItemOptionGroup itemOptionGroups) {
        this.itemOptionGroups.add(itemOptionGroups);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSalesPrice() {
        return salesPrice;
    }

    public List<ItemOptionGroup> getItemOptionGroups() {
        return itemOptionGroups;
    }
}