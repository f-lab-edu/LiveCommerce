package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.LoginCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.encryption.PasswordEncryption;

public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryption passwordEncryption;

    public UserLoginProcessor(UserRepository userRepository, PasswordEncryption passwordEncryption) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }


    public User execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail());

        if (idPasswordCheck(command, user)) {
            throw new IllegalStateException();
        }

        return user;
    }

    private boolean idPasswordCheck(LoginCommand command, User user) {
        return null == user || !passwordEncryption.match(command.getPassword(), user.getPassword());
    }
}
