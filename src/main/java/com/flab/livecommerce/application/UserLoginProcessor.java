package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.LoginCommand;
import com.flab.livecommerce.domain.user.Encryption;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;


public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final Encryption passwordEncryption;

    public UserLoginProcessor(UserRepository userRepository, Encryption passwordEncryption) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public void execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail());

        if (idPasswordCheck(command, user)) {
            throw new IllegalStateException();
        }
    }

    private boolean idPasswordCheck(LoginCommand command, User user) {
        return null == user || !passwordEncryption.match(command.getPassword(), user.getPassword());
    }
}
