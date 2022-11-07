package com.flab.livecommerce.user.domain;

public interface UserRepository {

    User save(User user);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
