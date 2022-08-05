package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.User;
import com.flab.livecommerce.domain.UserRepository;
import com.flab.livecommerce.presentation.request.UserCreateRequest;

public class UserCreateProcessor {

    private final UserRepository userRepository;

    public UserCreateProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UserCreateRequest userCreateRequest) {
        User user = new User(
            userCreateRequest.getEmail(),
            userCreateRequest.getPassword(),
            userCreateRequest.getNickname()
        );
        userRepository.save(user);
    }
}
