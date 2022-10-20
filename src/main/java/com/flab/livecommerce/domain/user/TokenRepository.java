package com.flab.livecommerce.domain.user;

public interface TokenRepository {

    void save(String token, String userId);

    String findByToken(String token);

    void remove(String token);
}
