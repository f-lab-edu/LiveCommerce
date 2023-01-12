package com.flab.item.infrastructure.persistence.jpa;

import com.flab.item.domain.Item;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);
}
