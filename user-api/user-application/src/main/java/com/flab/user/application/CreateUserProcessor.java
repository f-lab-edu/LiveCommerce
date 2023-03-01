package com.flab.user.application;

import com.flab.user.application.command.CreateUserCommand;
import com.flab.user.application.result.UserResult;
import com.flab.user.domain.PasswordEncryptor;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.UserDuplicatedEmailException;

public class CreateUserProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public CreateUserProcessor(
        UserRepository userRepository,
        PasswordEncryptor passwordEncryptor
    ) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public UserResult execute(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new UserDuplicatedEmailException();
        }

        String encryptedPassword = passwordEncryptor.encrypt(command.getPassword());
        var user = userRepository.save(command.toEntity(encryptedPassword));

        return UserResult.from(user);
    }
}
