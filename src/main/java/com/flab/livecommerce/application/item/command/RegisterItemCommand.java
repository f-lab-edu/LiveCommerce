package com.flab.livecommerce.application.item.command;

import com.flab.livecommerce.domain.item.Item;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterItemCommand {

    private String name;
    private Integer price;
    private Integer salesPrice;
    private String description;
    private Integer stockQuantity;
    private int modelNumber;
    private List<RegisterItemOptionGroupCommand> itemOptionGroup;

    public Item toEntity() {
        return Item.builder()
            .name(name)
            .description(description)
            .price(price)
            .salesPrice(salesPrice)
            .stockQuantity(stockQuantity)
            .modelNumber(modelNumber)
            .build();
    }
}
