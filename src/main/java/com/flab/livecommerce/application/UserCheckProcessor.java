package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.UserRepository;

public class UserCheckProcessor {

    private final UserRepository userRepository;

    public UserCheckProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String email) {
        if (null == userRepository.findByEmail(email)) {
            throw new IllegalStateException();
        }
    }
}
