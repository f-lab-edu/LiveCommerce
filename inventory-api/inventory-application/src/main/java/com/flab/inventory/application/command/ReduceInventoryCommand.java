package com.flab.inventory.application.command;

public class ReduceInventoryCommand {

    private Long itemId;
    private Integer count;

    protected ReduceInventoryCommand() {
    }

    public ReduceInventoryCommand(Long itemId, Integer count) {
        this.itemId = itemId;
        this.count = count;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getCount() {
        return count;
    }
}
