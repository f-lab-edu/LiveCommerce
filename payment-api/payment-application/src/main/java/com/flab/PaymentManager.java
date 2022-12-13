package com.flab;

import com.flab.command.PaymentStubCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class PaymentManager {

    private final ApplicationEventPublisher publisher;

    public PaymentManager(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void payed(PaymentStubCommand command) {
        
    }
}
