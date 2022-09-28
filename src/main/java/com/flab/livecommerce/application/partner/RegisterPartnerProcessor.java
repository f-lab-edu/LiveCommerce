package com.flab.livecommerce.application.partner;

import com.flab.livecommerce.application.partner.command.PartnerRegisterCommand;
import com.flab.livecommerce.domain.partner.Partner;
import com.flab.livecommerce.domain.partner.PartnerRepository;

public class RegisterPartnerProcessor {

    private final PartnerRepository partnerRepository;

    public RegisterPartnerProcessor(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Partner execute(PartnerRegisterCommand command) {
        return partnerRepository.save(command.toEntity());
    }

}

