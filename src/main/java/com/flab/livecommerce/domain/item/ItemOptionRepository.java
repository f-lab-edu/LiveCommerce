package com.flab.livecommerce.domain.item;

public interface ItemOptionRepository {

    ItemOption save(ItemOption itemOption);

    void update(ItemOption itemOption);
}
