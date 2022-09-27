package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.SearchItemProcessor;
import com.flab.livecommerce.application.item.UploadImageProcessor;
import com.flab.livecommerce.application.item.command.RegisterItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.item.exception.DuplicatedItemNameException;
import com.flab.livecommerce.domain.item.exception.DuplicatedModelNumException;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;

    private final UploadImageProcessor uploadImageProcessor;
    private final SearchItemProcessor searchItemProcessor;
    private final ItemRepository itemRepository;

    public ItemManager(
        RegisterItemProcessor registerItemProcessor,
        UploadImageProcessor uploadImageProcessor,
        SearchItemProcessor searchItemProcessor,
        ItemRepository itemRepository
    ) {
        this.registerItemProcessor = registerItemProcessor;
        this.uploadImageProcessor = uploadImageProcessor;
        this.searchItemProcessor = searchItemProcessor;
        this.itemRepository = itemRepository;
    }

    public Item register(RegisterItemCommand command, MultipartFile thumbnailImg)
        throws IOException {
        checkProductNameDuplicated(command);
        checkModelNumDuplicated(command);
        Item item = registerItemProcessor.execute(command);
        return uploadImageProcessor.execute(item, thumbnailImg);
    }

    public Item search(Long id) {
        return searchItemProcessor.execute(id);
    }

    public void checkModelNumDuplicated(RegisterItemCommand command) {
        if (null != itemRepository.findByModelNumber(command.getModelNumber())) {
            throw new DuplicatedModelNumException("이미 존재하는 모델 번호입니다.");
        }
    }

    public void checkProductNameDuplicated(RegisterItemCommand command) {
        if (null != itemRepository.findByName(command.getName())) {
            throw new DuplicatedItemNameException("이미 존재하는 상품명입니다.");
        }
    }
}
