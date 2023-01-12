package com.flab.item.application.command;

import java.util.List;

public class RegisterItemOptionGroupCommand {

    private Integer ordering;
    private String name;
    private List<RegisterItemOptionCommand> itemOptions;

    public RegisterItemOptionGroupCommand(
        Integer ordering,
        String name,
        List<RegisterItemOptionCommand> itemOptions
    ) {
        this.ordering = ordering;
        this.name = name;
        this.itemOptions = itemOptions;
    }

    private RegisterItemOptionGroupCommand() {
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }

    public List<RegisterItemOptionCommand> getItemOptions() {
        return itemOptions;
    }
}
