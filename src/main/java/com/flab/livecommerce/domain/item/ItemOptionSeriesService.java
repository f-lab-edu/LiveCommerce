package com.flab.livecommerce.domain.item;

import com.flab.livecommerce.application.item.command.ItemFormCommand;
import java.util.List;

public interface ItemOptionSeriesService {

    List<ItemOptionGroup> save(ItemFormCommand command, Item item);

    void update(ItemFormCommand command, Item item);
}
