package com.flab.user.application;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.user.domain.TokenRepository;

public class DummyTokenRepository implements TokenRepository {

    @Override
    public void save(AuthenticatedMember authenticatedMember) {

    }

    @Override
    public AuthenticatedMember findByToken(String token) {
        return null;
    }

    @Override
    public void renewExpirationSec(AuthenticatedMember authenticatedMember) {

    }

    @Override
    public void remove(String token) {

    }
}
