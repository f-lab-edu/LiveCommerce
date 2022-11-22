package com.flab.item.domain;

public interface ItemOptionRepository {

    ItemOption save(ItemOption itemOption);

    void update(ItemOption itemOption, Long optionId);
}
