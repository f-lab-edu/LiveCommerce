package com.flab.order.application.command;


public final class CreateOrderItemOptionCommand {

    private Integer ordering;
    private String name;
    private Integer price;

    public CreateOrderItemOptionCommand(Integer ordering, String name, Integer price) {
        this.ordering = ordering;
        this.name = name;
        this.price = price;
    }

    private CreateOrderItemOptionCommand() {
    }

    public Integer getOrdering() {
        return ordering;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
