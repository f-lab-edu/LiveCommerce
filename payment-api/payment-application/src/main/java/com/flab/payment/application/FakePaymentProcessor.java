package com.flab.payment.application;

import com.flab.payment.application.command.PaymentFakeCommand;
import com.flab.payment.domain.Payment;
import com.flab.payment.domain.PaymentRepository;

public class FakePaymentProcessor {

    private final PaymentRepository paymentRepository;

    public FakePaymentProcessor(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment execute(PaymentFakeCommand command) {
        var payment = Payment.create(command.getOrderId(), command.getTotalAmount());
        paymentRepository.save(payment);

        return payment;
    }
}
