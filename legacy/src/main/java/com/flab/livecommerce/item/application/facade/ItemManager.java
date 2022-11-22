package com.flab.livecommerce.item.application.facade;

<<<<<<< HEAD:toMove/application/item/facade/ItemManager.java
import com.flab.livecommerce.application.item.DeleteItemProcessor;
import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UpdateItemProcessor;
import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.application.item.command.UpdateItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.Item.Info;
=======
import com.flab.livecommerce.item.application.RegisterItemProcessor;
import com.flab.livecommerce.item.application.SearchItemProcessor;
import com.flab.livecommerce.item.application.UploadImageProcessor;
import com.flab.livecommerce.item.application.command.RegisterItemCommand;
import com.flab.livecommerce.item.domain.Item;
>>>>>>> main:legacy/src/main/java/com/flab/livecommerce/item/application/facade/ItemManager.java
import org.springframework.stereotype.Service;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;
    private final SearchItemProcessor searchItemProcessor;
    private final UpdateItemProcessor updateItemProcessor;
    private final DeleteItemProcessor deleteItemProcessor;

    public ItemManager(
        RegisterItemProcessor registerItemProcessor,
        SearchItemProcessor searchItemProcessor,
        UpdateItemProcessor updateItemProcessor,
        DeleteItemProcessor deleteItemProcessor
    ) {
        this.registerItemProcessor = registerItemProcessor;
        this.searchItemProcessor = searchItemProcessor;
        this.updateItemProcessor = updateItemProcessor;
        this.deleteItemProcessor = deleteItemProcessor;
    }

    public Item register(RegisterItemCommand command) {
        return registerItemProcessor.execute(command);
    }

    public Info search(Long id) {
        return searchItemProcessor.execute(id);
    }

    public void update(UpdateItemCommand command, Long id) {
        updateItemProcessor.execute(command, id);
    }

    public void delete(Long id) {
        deleteItemProcessor.execute(id);
    }

}
