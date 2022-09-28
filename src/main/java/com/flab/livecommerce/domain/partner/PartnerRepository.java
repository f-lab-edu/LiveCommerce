package com.flab.livecommerce.domain.partner;

import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository {

    Partner save(Partner partner);
}
