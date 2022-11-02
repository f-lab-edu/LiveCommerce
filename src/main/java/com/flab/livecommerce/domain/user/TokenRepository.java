package com.flab.livecommerce.domain.user;

import com.flab.livecommerce.common.auth.AuthenticatedUser;

public interface TokenRepository {

    void save(AuthenticatedUser authenticatedUser);

    AuthenticatedUser findByToken(String token);

    void renewExpirationSec(AuthenticatedUser authenticatedUser);

    void remove(String token);

}
