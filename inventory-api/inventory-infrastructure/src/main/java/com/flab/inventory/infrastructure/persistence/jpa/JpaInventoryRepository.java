package com.flab.inventory.infrastructure.persistence.jpa;

import com.flab.inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInventoryRepository extends JpaRepository<Inventory, Long> {

}
