package com.flab.livecommerce.infrastructure.item.persistence.jpa;

import com.flab.livecommerce.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long> {

}
