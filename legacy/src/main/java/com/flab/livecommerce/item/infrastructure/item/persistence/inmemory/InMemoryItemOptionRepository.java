package com.flab.livecommerce.item.infrastructure.item.persistence.inmemory;

import com.flab.livecommerce.item.domain.ItemOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryItemOptionRepository {

    private static Map<Long, ItemOption> itemOptionMap = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    public ItemOption save(ItemOption itemOption) {
        itemOptionMap.put(sequence.incrementAndGet(), itemOption);
        return itemOption;
    }
}
