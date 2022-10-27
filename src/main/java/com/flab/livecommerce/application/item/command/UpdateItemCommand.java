package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateItemCommand {

    private Long shopId;
    private String name;
    private Integer price;
    private Integer salesPrice;
    private String description;
    private Integer stockQuantity;

    private List<UpdateItemOptionGroupCommand> itemOptionGroups;

    public Item toEntity() {
        return Item.builder()
            .shopId(shopId)
            .name(name)
            .description(description)
            .price(price)
            .salesPrice(salesPrice)
            .stockQuantity(stockQuantity)
            .build();
    }
}
