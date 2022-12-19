package com.flab.inventory.infrastructure.persistence.jpa;

import com.flab.inventory.domain.Inventory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByItemId(Long id);
}
