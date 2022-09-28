package com.flab.livecommerce.presentation.partner;

import com.flab.livecommerce.application.partner.facade.PartnerManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.partner.request.RegisterPartnerRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/partner")
public class PartnerController {

    private final PartnerManager partnerManager;

    public PartnerController(PartnerManager partnerManager) {
        this.partnerManager = partnerManager;
    }

    @PostMapping
    public CommonApiResponse signUp(@RequestBody @Valid RegisterPartnerRequest request) {
        partnerManager.registerPartner(request.toCommand());
        return CommonApiResponse.success(null);
    }

}
