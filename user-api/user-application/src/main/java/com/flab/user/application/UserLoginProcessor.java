package com.flab.user.application;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.user.domain.PasswordEncryptor;
import com.flab.user.domain.TokenGenerator;
import com.flab.user.domain.TokenRepository;
import com.flab.user.domain.User;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.InvalidUserException;
import com.flab.user.domain.exception.PasswordNotMatchedException;


public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;
    private final PasswordEncryptor passwordEncryption;
    private final Long tokenExpirationSec;

    public UserLoginProcessor(
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository,
        PasswordEncryptor passwordEncryption,
        Long tokenExpirationSec
    ) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
        this.passwordEncryption = passwordEncryption;
        this.tokenExpirationSec = tokenExpirationSec;
    }

    public String execute(LoginCommand command) {
        var user = userRepository.findByEmail(command.getEmail());

        if (user == null) {
            throw new InvalidUserException();
        }

        if (!passwordCheck(command, user)) {
            throw new PasswordNotMatchedException();
        }

        var token = tokenGenerator.generate();

        tokenRepository.save(
            AuthenticatedUser.create(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole(),
                tokenExpirationSec
            )
        );

        return token;
    }

    private boolean passwordCheck(LoginCommand command, User loginUserInfo) {
        return passwordEncryption.match(command.getPassword(), loginUserInfo.getPassword());
    }

    public static class LoginCommand {

        private String email;
        private String password;

        public LoginCommand(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }
}
