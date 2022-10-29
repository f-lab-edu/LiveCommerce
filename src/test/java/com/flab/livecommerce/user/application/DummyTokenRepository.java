package com.flab.livecommerce.user.application;

import com.flab.livecommerce.common.auth.AuthenticatedUser;
import com.flab.livecommerce.domain.user.TokenRepository;

public class DummyTokenRepository implements TokenRepository {

    @Override
    public void save(AuthenticatedUser authenticatedUser) {

    }

    @Override
    public AuthenticatedUser findByToken(String token) {
        return null;
    }

    @Override
    public void remove(String token) {

    }
}
