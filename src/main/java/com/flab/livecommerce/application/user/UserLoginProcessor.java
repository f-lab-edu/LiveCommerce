package com.flab.livecommerce.application.user;

import com.flab.livecommerce.common.AuthenticatedUser;
import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.exception.InvalidUserException;
import com.flab.livecommerce.domain.user.exception.PasswordNotMatchedException;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;
    private final PasswordEncryptor passwordEncryption;

    public UserLoginProcessor(
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository,
        PasswordEncryptor passwordEncryption
    ) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
        this.passwordEncryption = passwordEncryption;
    }

    public String execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail());

        if (user == null) {
            throw new InvalidUserException();
        }

        if (!passwordCheck(command, user)) {
            throw new PasswordNotMatchedException();
        }

        var token = tokenGenerator.generate();
        tokenRepository.save(token, AuthenticatedUser.create(user));

        return token;
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
