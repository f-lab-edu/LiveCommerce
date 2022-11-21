package com.flab.user.application;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.user.domain.TokenRepository;

public class DummyTokenRepository implements TokenRepository {

    @Override
    public void save(AuthenticatedUser authenticatedUser) {

    }

    @Override
    public AuthenticatedUser findByToken(String token) {
        return null;
    }

    @Override
    public void renewExpirationSec(AuthenticatedUser authenticatedUser) {

    }

    @Override
    public void remove(String token) {

    }
}
