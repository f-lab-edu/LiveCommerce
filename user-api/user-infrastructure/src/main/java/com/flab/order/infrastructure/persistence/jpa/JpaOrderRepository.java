package com.flab.order.infrastructure.persistence.jpa;

import com.flab.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

}
