package com.flab.livecommerce.item.domain;

import com.flab.livecommerce.item.application.command.RegisterItemCommand;
import java.util.List;

public interface ItemOptionSeriesService {

    List<ItemOptionGroup> save(RegisterItemCommand command, Item item);
}
