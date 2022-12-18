package com.flab.payment.infrastructure.persistence.jpa;

import com.flab.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {

}
