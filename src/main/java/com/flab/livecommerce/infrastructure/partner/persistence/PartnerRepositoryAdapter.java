package com.flab.livecommerce.infrastructure.partner.persistence;

import com.flab.livecommerce.domain.partner.Partner;
import com.flab.livecommerce.domain.partner.PartnerRepository;
import com.flab.livecommerce.infrastructure.partner.persistence.jdbctemplate.JdbcTemplatePartnerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PartnerRepositoryAdapter implements PartnerRepository {

    private final JdbcTemplatePartnerRepository partnerRepository;

    public PartnerRepositoryAdapter(JdbcTemplatePartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Partner save(Partner partner) {
        return this.partnerRepository.save(partner);
    }
}
