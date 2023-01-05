package com.flab.livecommerce.user.application;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.livecommerce.user.domain.PasswordEncryptor;
import com.flab.livecommerce.user.domain.TokenGenerator;
import com.flab.livecommerce.user.domain.TokenRepository;
import com.flab.livecommerce.user.domain.User;
import com.flab.livecommerce.user.domain.UserRepository;
import com.flab.livecommerce.user.domain.exception.InvalidUserException;
import com.flab.livecommerce.user.domain.exception.PasswordNotMatchedException;
import com.flab.livecommerce.user.infrastructure.token.TokenProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;
    private final PasswordEncryptor passwordEncryption;
    private final TokenProperties tokenProperties;

    public UserLoginProcessor(
        UserRepository userRepository,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository,
        PasswordEncryptor passwordEncryptor,
        TokenProperties tokenProperties
    ) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
        this.passwordEncryption = passwordEncryptor;
        this.tokenProperties = tokenProperties;
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
                tokenProperties.getTokenExpirationSec()
            )
        );

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
