package com.flab.livecommerce.user.domain;

import com.flab.common.auth.AuthenticatedUser;

public interface TokenRepository {

    void save(AuthenticatedUser authenticatedUser);

    AuthenticatedUser findByToken(String token);

    void renewExpirationSec(AuthenticatedUser authenticatedUser);

    void remove(String token);

}
