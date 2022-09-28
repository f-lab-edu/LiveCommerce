package com.flab.livecommerce.infrastructure.partner.config;

import com.flab.livecommerce.application.partner.RegisterPartnerProcessor;
import com.flab.livecommerce.domain.partner.PartnerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartnerProcessorConfig {

    @Bean
    public RegisterPartnerProcessor registerPartnerProcessor(
        PartnerRepository partnerRepository
    ) {
        return new RegisterPartnerProcessor(
            partnerRepository
        );
    }
}
