package com.flab.item.application.command;

public class RegisterItemOptionCommand {

    private String name;
    private Integer ordering;
    private Integer price;

    public RegisterItemOptionCommand(String name, Integer ordering, Integer price) {
        this.name = name;
        this.ordering = ordering;
        this.price = price;
    }

    private RegisterItemOptionCommand() {
    }

    public String getName() {
        return name;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public Integer getPrice() {
        return price;
    }
}
