package com.flab.item.domain;

public class ItemOption {

    private Long id;
    private Long itemOptionGroupId;
    private String name;
    private Integer ordering;
    private Long price;

    protected ItemOption() {
    }

    public ItemOption(Long itemOptionGroupId, String name, Integer ordering, Long price) {
        this.itemOptionGroupId = itemOptionGroupId;
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

    public Long getItemOptionGroupId() {
        return itemOptionGroupId;
    }

    public String getName() {
        return name;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public Long getPrice() {
        return price;
    }
}
