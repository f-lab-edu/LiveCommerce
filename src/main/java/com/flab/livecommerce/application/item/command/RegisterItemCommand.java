package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterItemCommand {

    private Long sellerId;
    private String name;
    private Integer price;
    private Integer salesPrice;
    private String description;
    private Integer stockQuantity;

    private List<RegisterItemOptionGroupCommand> itemOptionGroup;

    public Item toEntity() {
        return Item.builder()
            .sellerId(sellerId)
            .name(name)
            .description(description)
            .price(price)
            .salesPrice(salesPrice)
            .stockQuantity(stockQuantity)
            .build();
    }
}
