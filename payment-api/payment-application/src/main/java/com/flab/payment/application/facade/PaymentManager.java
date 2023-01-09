package com.flab.payment.application.facade;

import com.flab.payment.application.FakePaymentProcessor;
import com.flab.payment.application.command.PaymentFakeCommand;
import org.springframework.stereotype.Service;

@Service
public class PaymentManager {

    private final FakePaymentProcessor fakePaymentProcessor;

    public PaymentManager(FakePaymentProcessor fakePaymentProcessor) {
        this.fakePaymentProcessor = fakePaymentProcessor;
    }

    public void payed(PaymentFakeCommand command) {
        var payment = fakePaymentProcessor.execute(command);
    }
}
