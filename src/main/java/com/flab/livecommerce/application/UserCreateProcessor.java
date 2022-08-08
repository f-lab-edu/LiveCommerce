package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
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

        User findUser = userRepository.findByEmail(user.getEmail());
        //중복 회원 검사
        if (null != findUser) {
            throw new RuntimeException();
        }

        userRepository.save(user);
    }
}
