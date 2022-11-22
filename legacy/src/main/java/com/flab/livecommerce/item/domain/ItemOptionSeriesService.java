package com.flab.livecommerce.item.domain;

<<<<<<< HEAD:toMove/domain/item/ItemOptionSeriesService.java
import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.application.item.command.UpdateItemCommand;
=======
import com.flab.livecommerce.item.application.command.RegisterItemCommand;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/domain/ItemOptionSeriesService.java
import java.util.List;

public interface ItemOptionSeriesService {

    List<ItemOptionGroup> save(RegisterItemCommand command, Item item);

    void update(UpdateItemCommand command, Item item);
}
