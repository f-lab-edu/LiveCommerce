package com.flab.livecommerce.domain.user;

public interface TokenRepository {

    void save(String token, User user);
}
