package com.flab.livecommerce.application.partner.command;

import com.flab.livecommerce.domain.partner.Partner;

public class PartnerRegisterCommand {

    public Partner toEntity() {
        return new Partner(null, null, null);
    }
}
