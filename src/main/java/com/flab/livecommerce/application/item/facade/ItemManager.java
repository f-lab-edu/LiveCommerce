package com.flab.livecommerce.application.item.facade;

import com.flab.livecommerce.application.item.ItemAddProcessor;
import com.flab.livecommerce.domain.item.ItemRepository;
import com.flab.livecommerce.domain.item.exception.DuplicatedModelNumException;
import com.flab.livecommerce.domain.item.exception.DuplicatedItemNameException;
import com.flab.livecommerce.presentation.item.request.ItemRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemManager {

    private final ItemAddProcessor itemAddProcessor;
    private final ItemRepository itemRepository;

    public ItemManager(
        ItemAddProcessor itemAddProcessor,
        ItemRepository itemRepository
    ) {
        this.itemAddProcessor = itemAddProcessor;
        this.itemRepository = itemRepository;
    }

    public void register(ItemRequest itemRequest) {
        itemAddProcessor.execute(itemRequest);
    }

    public void checkModelNumDuplicated(ItemRequest requestDto) {
        if (null != itemRepository.findByModelNumber(requestDto.getModelNumber())) {
            throw new DuplicatedModelNumException("이미 존재하는 모델 번호입니다.");
        }
    }

    public void checkProductNameDuplicated(ItemRequest requestDto) {
        if (null != itemRepository.findByName(requestDto.getName())) {
            throw new DuplicatedItemNameException("이미 존재하는 상품명입니다.");
        }
    }
}
