package com.flab.seller.domain;

public interface SessionRepository {

    void save(String sessionId, AuthenticatedMember authenticatedMember);

    AuthenticatedMember findBySessionId(String sessionId);

    void renewExpirationSec(AuthenticatedMember authenticatedMember);

    void remove(String sessionId);
}
