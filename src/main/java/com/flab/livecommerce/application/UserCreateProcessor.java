package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

        User findUser = userRepository.findByEmail(command.getEmail());

        if (null != findUser) {
            throw new IllegalStateException();
        }

        userRepository.save(
            new User(
                command.getEmail(),
                passwordEncryption.encrypt(command.getPassword()),
                command.getNickname()
            )
        );
    }

    @Getter
    @AllArgsConstructor
    public static class UserCreateCommand {

        private String email;
        private String password;
        private String nickname;
    }
}
