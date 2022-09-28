package com.flab.livecommerce.presentation.item.response;

import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.Item.Info;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.List;
import lombok.Getter;

@Getter
public class SearchItemResponse {

    private String name;
    private String description;
    private Integer price;
    private Integer salesPrice;
    private Integer stockQuantity;
    private List<ItemOptionGroup> itemOptionGroups;

    public SearchItemResponse(
        String name,
        String description,
        Integer price,
        Integer salesPrice,
        Integer stockQuantity,
        List<ItemOptionGroup> itemOptionGroups
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesPrice = salesPrice;
        this.stockQuantity = stockQuantity;
        this.itemOptionGroups = itemOptionGroups;
    }

    public static SearchItemResponse form(Info info) {

        return new SearchItemResponse(
            info.getName(),
            info.getDescription(),
            info.getPrice(),
            info.getSalesPrice(),
            info.getStockQuantity(),
            info.getItemOptionGroups()
        );
    }
}
