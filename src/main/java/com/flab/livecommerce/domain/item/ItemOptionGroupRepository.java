package com.flab.livecommerce.domain.item;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemOptionGroupRepository {

    ItemOptionGroup save(ItemOptionGroup itemOptionGroup);
}
