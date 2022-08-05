package com.flab.livecommerce.domain;

public interface UserRepository {

    User save(User user);

    User findByEmail(String email);
}
