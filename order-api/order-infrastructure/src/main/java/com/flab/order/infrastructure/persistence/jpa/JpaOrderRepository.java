package com.flab.order.infrastructure.persistence.jpa;

import com.flab.order.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {
        "orderLineItems",
        "orderLineItems.orderItemOptionGroups",
        "orderLineItems.orderItemOptionGroups.orderItemOptions"}
    )
    Optional<Order> findById(Long id);
}
