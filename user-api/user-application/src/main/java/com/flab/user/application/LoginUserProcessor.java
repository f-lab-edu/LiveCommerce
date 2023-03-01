package com.flab.user.application;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.user.application.command.LoginUserCommand;
import com.flab.user.domain.PasswordEncryptor;
import com.flab.user.domain.TokenGenerator;
import com.flab.user.domain.TokenRepository;
import com.flab.user.domain.User;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.UserPasswordNotMatchedException;

public class LoginUserProcessor {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final Long tokenExpirationSec;

    public LoginUserProcessor(
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository,
        PasswordEncryptor passwordEncryptor,
        Long tokenExpirationSec
    ) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
        this.passwordEncryptor = passwordEncryptor;
        this.tokenExpirationSec = tokenExpirationSec;
    }

    public String execute(LoginUserCommand command) {
        var user = userRepository.findByEmail(command.getEmail());

        if (!checkPassword(command, user)) {
            throw new UserPasswordNotMatchedException();
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

    private boolean checkPassword(LoginUserCommand command, User user) {
        return passwordEncryptor.match(command.getPassword(), user.getPassword());
    }
}
