package com.flab.livecommerce.item.application.facade;

import com.flab.livecommerce.item.application.RegisterItemProcessor;
import com.flab.livecommerce.item.application.SearchItemProcessor;
import com.flab.livecommerce.item.application.UploadImageProcessor;
import com.flab.livecommerce.item.application.command.RegisterItemCommand;
import com.flab.livecommerce.item.domain.Item;
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

    public Item search(Long id) {
        return searchItemProcessor.execute(id);
    }

}
