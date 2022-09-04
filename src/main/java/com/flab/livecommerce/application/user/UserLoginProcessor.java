package com.flab.livecommerce.application.user;

import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.exception.InvalidUserException;
import com.flab.livecommerce.domain.user.exception.PasswordNotMatchedException;
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
        User user = userRepository.findByEmail(command.getEmail());

        if (null == user) {
            throw new InvalidUserException("존재하지 않는 회원입니다.");
        }

        if (!passwordCheck(command, user)) {
            throw new PasswordNotMatchedException("패스워드가 일치하지 않습니다.");
        }

        return user;
    }

    private boolean passwordCheck(LoginCommand command, User loginUserInfo) {
        return passwordEncryption.match(command.getPassword(), loginUserInfo.getPassword());
    }

    @Getter
    @AllArgsConstructor
    public static class LoginCommand {

        private String email;
        private String password;
    }
}
