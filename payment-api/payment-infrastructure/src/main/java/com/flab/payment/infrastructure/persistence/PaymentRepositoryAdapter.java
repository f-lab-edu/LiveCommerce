package com.flab.payment.infrastructure.persistence;

import com.flab.payment.domain.Payment;
import com.flab.payment.domain.PaymentRepository;
import com.flab.payment.infrastructure.persistence.jpa.JpaPaymentRepository;

public class PaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;

    public PaymentRepositoryAdapter(JpaPaymentRepository jpaPaymentRepository) {
        this.paymentRepository = jpaPaymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
