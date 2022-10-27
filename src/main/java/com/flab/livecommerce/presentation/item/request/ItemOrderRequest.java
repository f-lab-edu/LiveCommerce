package com.flab.livecommerce.presentation.item.request;

import com.flab.livecommerce.application.item.command.UpdateImageOrderCommand;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemOrderRequest {
    private List<Long> imageIdList;
    private List<Integer> orderingList;

    public UpdateImageOrderCommand toCommand() {
        return new UpdateImageOrderCommand(imageIdList, orderingList);
    }
}
