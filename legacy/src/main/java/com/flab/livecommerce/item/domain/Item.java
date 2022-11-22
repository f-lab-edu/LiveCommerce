package com.flab.livecommerce.item.domain;

<<<<<<< HEAD:toMove/domain/item/Item.java
import com.flab.livecommerce.common.exception.InvalidParameterException;
import com.flab.livecommerce.domain.image.ItemImage;
=======
import com.flab.common.exception.InvalidParameterException;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/domain/Item.java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {

    private Long id;
    private Long sellerId;
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
        Long sellerId,
        String name,
        String description,
        Long price,
        Long salesPrice,
        Integer stockQuantity
    ) {
        if (sellerId == null) {
            throw new InvalidParameterException("Item.sellerId");
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

        this.sellerId = sellerId;
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

    public Item addItemImage(ItemImage itemImage) {
        this.itemImages.add(itemImage);
        return this;
    }

    public List<String> findItemImageUris(String uriPrefix) {
        return this.getItemImages().stream().map(
            itemImage -> uriPrefix + itemImage.getUrl()
        ).collect(Collectors.toList());
    }


    @Getter
    public static class Info {

        private Long sellerId;
        private String name;
        private String description;
        private Long price;
        private Long salesPrice;
        private Integer stockQuantity;
        private List<ItemOptionGroup> itemOptionGroups;
        private List<String> itemImageUrls;

<<<<<<< HEAD:toMove/domain/item/Item.java
        public Info(Item item, String uriPrefix) {
            this.shopId = item.getShopId();
=======
        public Info(Item item, List<ItemOptionGroup> itemOptionGroups) {
            this.sellerId = item.getSellerId();
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/domain/Item.java
            this.name = item.getName();
            this.description = item.getDescription();
            this.price = item.getPrice();
            this.salesPrice = item.getSalesPrice();
            this.salesPrice = item.getSalesPrice();
            this.stockQuantity = item.getStockQuantity();
            this.itemOptionGroups = item.getItemOptionGroups();
            this.itemImageUrls = item.findItemImageUris(uriPrefix);
        }
    }
}