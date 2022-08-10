package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.CreateCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.encryption.PasswordEncryption;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryption passwordEncryption;

    public UserCreateProcessor(UserRepository userRepository,
            PasswordEncryption passwordEncryption) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }


    public void execute(CreateCommand command) {
        String encryptedPassword = passwordEncryption.encrypt(command.getPassword());
        User user = new User(command.getEmail(), encryptedPassword, command.getNickname());
        userRepository.save(user);
    }
}
