package com.flab.livecommerce.presentation.item.request;

import com.flab.livecommerce.application.item.command.UpdateImageOrderingCommand;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateImageOrderingRequest {
    private List<Long> imageIdList;
    private List<Integer> orderingList;

    public UpdateImageOrderingCommand toCommand() {
        return new UpdateImageOrderingCommand(imageIdList, orderingList);
    }
}
