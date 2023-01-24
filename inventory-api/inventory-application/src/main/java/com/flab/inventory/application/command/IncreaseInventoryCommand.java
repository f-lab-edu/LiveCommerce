package com.flab.inventory.application.command;

public final class IncreaseInventoryCommand {

    private Long itemId;
    private Integer count;

    private IncreaseInventoryCommand() {
    }

    public IncreaseInventoryCommand(Long itemId, Integer count) {
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
