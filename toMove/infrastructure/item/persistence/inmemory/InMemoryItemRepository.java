package com.flab.livecommerce.infrastructure.item.persistence.inmemory;

import com.flab.livecommerce.domain.item.Item;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryItemRepository {

    private static Map<Long, Item> itemMap = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    public Item save(Item item) {
        itemMap.put(sequence.incrementAndGet(), item);
        return item;
    }

}
