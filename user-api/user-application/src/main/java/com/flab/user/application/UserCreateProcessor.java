package com.flab.user.application;

import com.flab.user.application.command.UserCreateCommand;
import com.flab.user.domain.PasswordEncryptor;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.DuplicatedEmailException;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryption;

    public UserCreateProcessor(
        UserRepository userRepository,
        PasswordEncryptor passwordEncryption
    ) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public void execute(UserCreateCommand command) {

        if (userRepository.existsByEmail(command.getEmail())) {
            throw new DuplicatedEmailException();
        }

        String encryptedPassword = passwordEncryption.encrypt(command.getPassword());
        userRepository.save(command.toEntity(encryptedPassword));
    }
}
