package com.flab.livecommerce.application.partner.facade;

import com.flab.livecommerce.application.partner.RegisterPartnerProcessor;
import com.flab.livecommerce.application.partner.command.PartnerRegisterCommand;
import com.flab.livecommerce.domain.partner.Partner;
import org.springframework.stereotype.Service;

@Service
public class PartnerManager {

    private final RegisterPartnerProcessor registerPartnerProcessor;

    public PartnerManager(RegisterPartnerProcessor registerPartnerProcessor) {
        this.registerPartnerProcessor = registerPartnerProcessor;
    }

    public Partner registerPartner(PartnerRegisterCommand command) {
        return registerPartnerProcessor.execute(command);
    }
}
