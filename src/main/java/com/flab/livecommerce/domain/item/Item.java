package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO shop은 다른 pr과 병합 / seller login 구현 후 병합
    private Long shopId;
    private String name;
    //상품 설명
    private String description;
    //상품 원가
    @Column(columnDefinition = "Integer")
    private Long price;
    //상품 판매가
    @Column(name = "sales_price", columnDefinition = "Integer")
    private Long salesPrice;
    //상품 재고
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    //옵션 그룹
    @OneToMany(mappedBy = "item")
    private List<ItemOptionGroup> itemOptionGroups = new ArrayList<>();

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
        itemOptionGroup.setItem(this);
        return this;
    }

    public List<Long> getOptionGroupId() {
        return this.itemOptionGroups.stream()
            .map(ItemOptionGroup::getId)
            .collect(Collectors.toList());
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