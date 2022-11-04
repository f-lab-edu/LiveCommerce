package com.flab.livecommerce.order.infrastructure.persistence.jpa;

import com.flab.livecommerce.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

}
