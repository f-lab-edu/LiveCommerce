package com.flab.item.domain;

import java.util.ArrayList;
import java.util.List;

// TODO 타이틀이미지, 상세 이미지 리스트 추가
public class Item {

    private Long id;
    private Long sellerId;
    private String name;
    //상품 설명
    private String description;
    //상품 원가
    private Integer price;
    //상품 판매가
    private Integer salesPrice;
    //상품 재고
    private Integer stockQuantity;

    //옵션 그룹
    private List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    protected Item() {
    }

    public Item(
        Long sellerId,
        String name,
        String description,
        Integer price,
        Integer salesPrice,
        Integer stockQuantity,
        List<ItemOptionGroup> itemOptionGroups
    ) {
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesPrice = salesPrice;
        this.stockQuantity = stockQuantity;
        this.itemOptionGroups = itemOptionGroups;
    }

    public Item addItemOptionGroup(ItemOptionGroup itemOptionGroups) {
        this.itemOptionGroups.add(itemOptionGroups);
        return this;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
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

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public List<ItemOptionGroup> getItemOptionGroups() {
        return itemOptionGroups;
    }
}