package com.flab.payment.application;

import com.flab.payment.application.command.PaymentFakeCommand;
import com.flab.payment.domain.Payment;
import com.flab.payment.domain.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

public class FakePaymentProcessor {

    private final PaymentRepository paymentRepository;
    private final ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(FakePaymentProcessor.class);

    public FakePaymentProcessor(
        PaymentRepository paymentRepository,
        ApplicationEventPublisher publisher
    ) {
        this.paymentRepository = paymentRepository;
        this.publisher = publisher;
    }

    @Transactional
    public Payment execute(PaymentFakeCommand command) {
        var payment = Payment.create(command.getOrderId(), command.getTotalAmount());
        paymentRepository.save(payment);
        payment.pollAllEvents().forEach(publisher::publishEvent);

        return payment;
    }
}
