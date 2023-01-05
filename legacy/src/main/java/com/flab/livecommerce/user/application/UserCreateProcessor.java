package com.flab.livecommerce.user.application;

import com.flab.livecommerce.user.application.command.UserCreateCommand;
import com.flab.livecommerce.user.domain.PasswordEncryptor;
import com.flab.livecommerce.user.domain.UserRepository;
import com.flab.livecommerce.user.domain.exception.DuplicatedEmailException;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryption;

    public UserCreateProcessor(
        UserRepository userRepository,
        PasswordEncryptor passwordEncryptor
    ) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryptor;
    }

    public void execute(UserCreateCommand command) {

        if (userRepository.existsByEmail(command.getEmail())) {
            throw new DuplicatedEmailException();
        }

        String encryptedPassword = passwordEncryption.encrypt(command.getPassword());
        userRepository.save(command.toEntity(encryptedPassword));
    }
}
