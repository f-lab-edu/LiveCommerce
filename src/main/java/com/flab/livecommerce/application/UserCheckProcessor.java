package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;

public class UserCheckProcessor {

    private final UserRepository userRepository;

    public UserCheckProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkEmail(String email) {
        User findUser = userRepository.findByEmail(email);
        if (null != findUser) {
            throw new IllegalStateException();
        }

        return true;
    }
}
