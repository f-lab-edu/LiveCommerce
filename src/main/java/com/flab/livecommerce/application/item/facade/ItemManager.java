package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.domain.item.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;

    private final UploadImageProcessor uploadImageProcessor;
    private final SearchItemProcessor searchItemProcessor;

    public ItemManager(
        RegisterItemProcessor registerItemProcessor,
        UploadImageProcessor uploadImageProcessor,
        SearchItemProcessor searchItemProcessor
    ) {
        this.registerItemProcessor = registerItemProcessor;
        this.uploadImageProcessor = uploadImageProcessor;
        this.searchItemProcessor = searchItemProcessor;
    }

    public Item register(RegisterItemCommand command) {
        return registerItemProcessor.execute(command);
    }

    public void registerItemImage(MultipartFile thumbnailImg) {
        //uploadImageProcessor.execute(item, thumbnailImg);
    }

    public Item.Info search(Long id) {
        return searchItemProcessor.execute(id);
    }

}
