package com.flab.request;

import com.flab.command.PaymentStubCommand;

public class PaymentStubRequest {

    public PaymentStubCommand toCommand() {
        return new PaymentStubCommand();
    }
}
