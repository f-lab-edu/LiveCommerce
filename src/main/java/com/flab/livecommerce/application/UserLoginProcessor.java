package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.presentation.request.UserLoginRequest;

public class UserLoginProcessor {

    private final UserRepository userRepository;

    public UserLoginProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());

        if (null == user) {
            throw new RuntimeException();
        }

        return user;
    }
}
