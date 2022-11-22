package com.flab.livecommerce.item.presentation.item.response;

<<<<<<< HEAD:toMove/presentation/item/response/SearchItemResponse.java
import com.flab.livecommerce.domain.item.Item.Info;
import com.flab.livecommerce.domain.item.ItemOptionGroup;
=======
import com.flab.livecommerce.item.domain.Item.Info;
import com.flab.livecommerce.item.domain.ItemOptionGroup;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/presentation/item/response/SearchItemResponse.java
import java.util.List;
import lombok.Getter;

@Getter
public class SearchItemResponse {

    private Long sellerId;
    private String name;
    private String description;
    private Long price;
    private Long salesPrice;
    private Integer stockQuantity;
    private List<ItemOptionGroup> itemOptionGroups;
    private List<String> itemImageUrls;

    public SearchItemResponse(
        Long sellerId,
        String name,
        String description,
        Long price,
        Long salesPrice,
        Integer stockQuantity,
        List<ItemOptionGroup> itemOptionGroups,
        List<String> itemImageUrls

    ) {
        this.sellerId = sellerId;
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
            info.getSellerId(),
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
