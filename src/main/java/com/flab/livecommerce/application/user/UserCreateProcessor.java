package com.flab.livecommerce.application.user;

import com.flab.livecommerce.application.user.command.UserCreateCommand;
import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.exception.DuplicatedEmailException;

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
