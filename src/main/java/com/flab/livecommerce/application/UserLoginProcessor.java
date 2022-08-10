package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.LoginCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserLoginProcessor(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail());

        if (idPasswordCheck(command, user)) {
            throw new IllegalStateException();
        }

        return user;
    }

    private boolean idPasswordCheck(LoginCommand command, User user) {
        return null == user || !encoder.matches(command.getPassword(), user.getPassword());
    }
}
