package com.flab.livecommerce.domain.item.event;

import lombok.Getter;

@Getter
public class ItemDeletedEvent {
    private Long itemId;

    public ItemDeletedEvent(Long itemId) {
        this.itemId = itemId;
    }
}
