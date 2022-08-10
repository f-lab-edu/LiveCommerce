package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.CreateCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserCreateProcessor(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void execute(CreateCommand command) {

        User findUser = userRepository.findByEmail(command.getEmail());
        //중복 회원 검사
        if (null != findUser) {
            throw new IllegalStateException();
        }

        userRepository.save(command.toUser());
    }
}
