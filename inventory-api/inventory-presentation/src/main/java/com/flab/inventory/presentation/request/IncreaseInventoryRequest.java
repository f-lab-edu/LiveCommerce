package com.flab.inventory.presentation.request;

import com.flab.inventory.application.command.IncreaseInventoryCommand;
import javax.validation.constraints.NotNull;

public class IncreaseInventoryRequest {

    @NotNull(message = "itemId 를 작성하세요")
    private Long itemId;

    @NotNull(message = "count 를 작성하세요.")
    private Integer count;

    protected IncreaseInventoryRequest() {
    }

    public IncreaseInventoryCommand toCommand() {
        return new IncreaseInventoryCommand(itemId, count);
    }

    public Integer getCount() {
        return count;
    }
}
