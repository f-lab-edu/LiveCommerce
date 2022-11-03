package com.flab.livecommerce.infrastructure.order.persistence.jpa;

import com.flab.livecommerce.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

}
