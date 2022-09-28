package com.flab.livecommerce.infrastructure.item.persistence.inmemory;

import com.flab.livecommerce.domain.item.ItemOptionGroup;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryItemOptionGroupRepository {

    private static Map<Long, ItemOptionGroup> itemOptionGroupMap = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    public ItemOptionGroup save(ItemOptionGroup itemOptionGroup) {
        itemOptionGroupMap.put(sequence.incrementAndGet(), itemOptionGroup);
        return itemOptionGroup;
    }
}
