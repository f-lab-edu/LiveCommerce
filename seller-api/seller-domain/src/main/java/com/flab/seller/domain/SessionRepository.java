package com.flab.seller.domain;

import com.flab.common.auth.AuthenticatedMember;

public interface SessionRepository {

    void save(String sessionId, AuthenticatedMember authenticatedMember);

    AuthenticatedMember findBySessionId(String sessionId);

    void renewExpirationSec(AuthenticatedMember authenticatedMember);

    void remove(String sessionId);
}
