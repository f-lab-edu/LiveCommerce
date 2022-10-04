package com.flab.livecommerce.domain.item;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemOptionGroupRepository {

    Long save(ItemOptionGroup itemOptionGroup);

    Long update(ItemOptionGroup optionGroup);

    ItemOptionGroup findById(Long itemOptionGroupId);
}
