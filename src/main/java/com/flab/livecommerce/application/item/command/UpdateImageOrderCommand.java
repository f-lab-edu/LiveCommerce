package com.flab.livecommerce.application.item.command;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateImageOrderCommand {

    private List<Long> imageIdList;
    private List<Integer> orderingList;
}
