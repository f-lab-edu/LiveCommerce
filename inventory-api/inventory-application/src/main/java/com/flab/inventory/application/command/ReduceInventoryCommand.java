package com.flab.inventory.application.command;

public final class ReduceInventoryCommand {

    private Long itemId;
    private Integer count;

    private ReduceInventoryCommand() {
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
