package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {

    private Long id;
    private Long shopId;
    private String name;
    //상품 설명
    private String description;
    //상품 원가
    private Long price;
    //상품 판매가
    private Long salesPrice;
    //상품 재고
    private Integer stockQuantity;

    //옵션 그룹
    private List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

    // 아이템 이미지 리스트
    private List<ItemImage> itemImages = new ArrayList<>();

    protected Item() {
    }

    @Builder
    public Item(
        Long shopId,
        String name,
        String description,
        Long price,
        Long salesPrice,
        Integer stockQuantity
    ) {
        if (shopId == null) {
            throw new InvalidParameterException("Item.shopId");
        }
        if (name == null && (name.length() == 0)) {
            throw new InvalidParameterException("Item.name");
        }
        if (description == null && (description.length() == 0)) {
            throw new InvalidParameterException("Item.description");
        }
        if (price == null) {
            throw new InvalidParameterException("Item.price");
        }
        if (salesPrice == null) {
            throw new InvalidParameterException("Item.salesPrice");
        }
        if (stockQuantity == null) {
            throw new InvalidParameterException("Item.description");
        }

        this.shopId = shopId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesPrice = salesPrice;
        this.stockQuantity = stockQuantity;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public Item addItemOptionGroup(ItemOptionGroup itemOptionGroup) {
        this.itemOptionGroups.add(itemOptionGroup);
        return this;
    }

    public void addItemImageList(ItemImage... itemImages) {
        for (ItemImage itemImage : itemImages) {
            itemImage.setOrdering(this.itemImages.size() + 1);
            this.itemImages.add(itemImage);
        }
    }

    public List<String> findItemImageUris() {
        return null;
    }

    @Getter
    public static class Info {

        private Long shopId;
        private String name;
        private String description;
        private Long price;
        private Long salesPrice;
        private Integer stockQuantity;
        private List<ItemOptionGroup> itemOptionGroups;

        public Info(Item item, List<ItemOptionGroup> itemOptionGroups) {
            this.shopId = item.getShopId();
            this.name = item.getName();
            this.description = item.getDescription();
            this.price = item.getPrice();
            this.salesPrice = item.getSalesPrice();
            this.salesPrice = item.getSalesPrice();
            this.stockQuantity = item.getStockQuantity();
            this.itemOptionGroups = itemOptionGroups;
        }
    }
}