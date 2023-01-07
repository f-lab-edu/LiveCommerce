package com.flab.user.domain;

import com.flab.common.auth.AuthenticatedMember;

public interface TokenRepository {

    void save(AuthenticatedMember authenticatedMember);

    AuthenticatedMember findByToken(String token);

    void renewExpirationSec(AuthenticatedMember authenticatedMember);

    void remove(String token);

}
