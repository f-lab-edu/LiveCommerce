package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryption;

    public UserLoginProcessor(UserRepository userRepository, PasswordEncryptor passwordEncryption) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public void execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail());

        if (!idPasswordCheck(command, user)) {
            throw new IllegalStateException();
        }

    }

    private boolean idPasswordCheck(LoginCommand command, User user) {
        return null != user && passwordEncryption.match(command.getPassword(), user.getPassword());
    }

    @Getter
    @AllArgsConstructor
    public static class LoginCommand {

        private String email;
        private String password;
    }
}
