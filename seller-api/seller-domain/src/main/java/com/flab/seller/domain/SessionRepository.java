package com.flab.seller.domain;

import com.flab.common.auth.AuthenticatedSeller;

public interface SessionRepository {

    void save(String sessionId, AuthenticatedSeller authenticatedSeller);

    AuthenticatedSeller findBySessionId(String sessionId);

    void renewExpirationSec(AuthenticatedSeller authenticatedSeller);

    void remove(String sessionId);
}
