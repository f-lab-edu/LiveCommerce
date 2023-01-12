package com.flab.item.application.command;

import com.flab.item.domain.ItemOption;
import com.flab.item.domain.ItemOptionGroup;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterItemCommand {

    private String name;
    private Integer price;
    private Integer salesPrice;
    private String description;
    private List<RegisterItemOptionGroupCommand> itemOptionGroup;

    public RegisterItemCommand(
        String name,
        Integer price,
        Integer salesPrice,
        String description,
        List<RegisterItemOptionGroupCommand> itemOptionGroup
    ) {
        this.name = name;
        this.price = price;
        this.salesPrice = salesPrice;
        this.description = description;
        this.itemOptionGroup = itemOptionGroup;
    }

    private RegisterItemCommand() {
    }

    public List<ItemOptionGroup> toOptionGroups() {
        return this.itemOptionGroup.stream().map(
            optionGroupCommand -> new ItemOptionGroup(
                optionGroupCommand.getName(),
                optionGroupCommand.getOrdering(),
                toOption(optionGroupCommand)
            )
        ).collect(Collectors.toList());
    }

    private List<ItemOption> toOption(RegisterItemOptionGroupCommand command) {
        return command.getItemOptions().stream().map(
            optionCommand -> new ItemOption(
                optionCommand.getName(),
                optionCommand.getOrdering(),
                optionCommand.getPrice()
            )
        ).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSalesPrice() {
        return salesPrice;
    }

    public String getDescription() {
        return description;
    }

    public List<RegisterItemOptionGroupCommand> getItemOptionGroup() {
        return itemOptionGroup;
    }
}