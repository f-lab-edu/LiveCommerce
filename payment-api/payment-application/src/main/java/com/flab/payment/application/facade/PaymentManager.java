package com.flab.payment.application.facade;

import com.flab.payment.application.FakePaymentProcessor;
import com.flab.payment.application.command.PaymentFakeCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentManager {

    private final ApplicationEventPublisher publisher;
    private final FakePaymentProcessor fakePaymentProcessor;

    public PaymentManager(ApplicationEventPublisher publisher,
        FakePaymentProcessor fakePaymentProcessor
    ) {
        this.publisher = publisher;
        this.fakePaymentProcessor = fakePaymentProcessor;
    }

    @Transactional
    public void payed(PaymentFakeCommand command) {
        var payment = fakePaymentProcessor.execute(command);
        payment.pollAllEvents().forEach(publisher::publishEvent);
    }
}
