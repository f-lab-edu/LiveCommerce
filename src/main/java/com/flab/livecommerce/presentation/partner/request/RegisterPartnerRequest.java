package com.flab.livecommerce.presentation.partner.request;

import com.flab.livecommerce.application.partner.command.PartnerRegisterCommand;

public class RegisterPartnerRequest {

    public PartnerRegisterCommand toCommand() {
        return new PartnerRegisterCommand();
    }
}
