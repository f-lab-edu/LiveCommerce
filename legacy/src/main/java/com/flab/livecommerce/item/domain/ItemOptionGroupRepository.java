package com.flab.livecommerce.item.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemOptionGroupRepository {

    ItemOptionGroup save(ItemOptionGroup itemOptionGroup);
}
