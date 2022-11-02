package com.flab.livecommerce.domain.user;

public interface UserRepository {

    User save(User user);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
