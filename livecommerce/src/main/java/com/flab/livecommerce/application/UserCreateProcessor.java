package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.User;
import com.flab.livecommerce.domain.UserRepository;

public class UserCreateProcessor {

    private final UserRepository userRepository;

    public UserCreateProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user) {

        userRepository.save(user);
    }
}
