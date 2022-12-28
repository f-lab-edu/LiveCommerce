package com.flab.inventory.presentation.request;

import com.flab.inventory.application.command.ReduceInventoryCommand;
import javax.validation.constraints.NotNull;

public class ReduceInventoryRequest {

    @NotNull(message = "itemId 를 작성하세요")
    private Long itemId;

    @NotNull(message = "count 를 작성하세요.")
    private Integer count;

    protected ReduceInventoryRequest() {
    }

    public ReduceInventoryCommand toCommand() {
        return new ReduceInventoryCommand(itemId, count);
    }

    public Integer getCount() {
        return count;
    }

}
