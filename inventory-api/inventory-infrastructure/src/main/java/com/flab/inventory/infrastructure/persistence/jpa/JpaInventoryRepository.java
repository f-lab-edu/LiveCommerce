package com.flab.inventory.infrastructure.persistence.jpa;

import com.flab.inventory.domain.Inventory;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface JpaInventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByItemId(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findByItemIdIn(Iterable<Long> itemIds);
}
