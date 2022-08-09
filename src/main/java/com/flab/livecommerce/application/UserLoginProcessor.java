package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserLoginProcessor(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User execute(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());

        if (idPasswordCheck(userLoginRequest, user)) {
            throw new IllegalStateException();
        }

        return user;
    }

    private boolean idPasswordCheck(UserLoginRequest userLoginRequest, User user) {
        return null == user || !encoder.matches(userLoginRequest.getPassword(), user.getPassword());
    }
}
