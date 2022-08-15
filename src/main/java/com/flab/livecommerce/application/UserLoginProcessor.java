package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryption;

    public UserLoginProcessor(
        UserRepository userRepository,
        PasswordEncryptor passwordEncryption
    ) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public User execute(LoginCommand command) {
        User loginUserInfo = userRepository.findByEmail(command.getEmail());

        if (!idPasswordCheck(command, loginUserInfo)) {
            throw new IllegalStateException();
        }
        return loginUserInfo;
    }

    private boolean idPasswordCheck(LoginCommand command, User loginUserInfo) {
        return null != loginUserInfo && passwordEncryption.match(command.getPassword(),
            loginUserInfo.getPassword());
    }

    @Getter
    @AllArgsConstructor
    public static class LoginCommand {

        private String email;
        private String password;
    }
}
