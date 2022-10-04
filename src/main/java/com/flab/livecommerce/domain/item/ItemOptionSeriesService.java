package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import java.util.List;

public interface ItemOptionSeriesService {

    List<ItemOptionGroup> save(RegisterItemCommand command, Item item);

    void update(RegisterItemCommand command, Long id);
}
