package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.CreateCommand;
import com.flab.livecommerce.domain.user.Encryption;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final Encryption passwordEncryption;

    public UserCreateProcessor(
        UserRepository userRepository,
        Encryption passwordEncryption
    ) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public void execute(CreateCommand command) {
        userRepository.save(
            new User(
                command.getEmail(),
                passwordEncryption.encrypt(command.getPassword()),
                command.getNickname()
            )
        );
    }
}
