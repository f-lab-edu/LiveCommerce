package com.flab.livecommerce.presentation.item.response;

import com.flab.livecommerce.domain.item.Item.Info;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import lombok.Getter;

@Getter
public class SearchItemResponse {

    private Long shopId;
    private String name;
    private String description;
    private Long price;
    private Long salesPrice;
    private Integer stockQuantity;
    private List<ItemOptionGroup> itemOptionGroups;
    private List<String> itemImageUrls;

    public SearchItemResponse(
        Long shopId,
        String name,
        String description,
        Long price,
        Long salesPrice,
        Integer stockQuantity,
        List<ItemOptionGroup> itemOptionGroups,
        List<String> itemImageUrls

    ) {
        this.shopId = shopId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesPrice = salesPrice;
        this.stockQuantity = stockQuantity;
        this.itemOptionGroups = itemOptionGroups;
        this.itemImageUrls = itemImageUrls;
    }

    public static SearchItemResponse form(Info info) {

        return new SearchItemResponse(
            info.getShopId(),
            info.getName(),
            info.getDescription(),
            info.getPrice(),
            info.getSalesPrice(),
            info.getStockQuantity(),
            info.getItemOptionGroups(),
            info.getItemImageUrls()
        );
    }
}
