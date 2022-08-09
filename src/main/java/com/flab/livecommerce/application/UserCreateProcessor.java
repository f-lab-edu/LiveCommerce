package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserCreateProcessor(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void execute(UserCreateRequest userCreateRequest) {
        User user = new User(
            userCreateRequest.getEmail(),
            encoder.encode(userCreateRequest.getPassword()),
            userCreateRequest.getNickname()
        );

        User findUser = userRepository.findByEmail(user.getEmail());
        //중복 회원 검사
        if (null != findUser) {
            throw new IllegalStateException();
        }

        userRepository.save(user);
    }
}
