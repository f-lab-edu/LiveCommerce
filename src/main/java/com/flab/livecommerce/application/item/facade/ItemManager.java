package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.RegisterItemProcessor;
import com.flab.livecommerce.application.item.RegisterItemProcessor.RegisterItemCommand;
import com.flab.livecommerce.domain.item.Item;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.item.exception.DuplicatedItemNameException;
import com.flab.livecommerce.domain.item.exception.DuplicatedModelNumException;
import org.springframework.stereotype.Service;

@Service
public class ItemManager {

    private final RegisterItemProcessor registerItemProcessor;
    private final ItemRepository itemRepository;

    public ItemManager(
        RegisterItemProcessor registerItemProcessor,
        ItemRepository itemRepository
    ) {
        this.registerItemProcessor = registerItemProcessor;
        this.itemRepository = itemRepository;
    }

    public Item register(RegisterItemCommand command) {
        checkProductNameDuplicated(command);
        checkModelNumDuplicated(command);
        return registerItemProcessor.execute(command);
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
