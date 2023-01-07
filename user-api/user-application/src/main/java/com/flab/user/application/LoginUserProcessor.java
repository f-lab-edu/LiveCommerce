package com.flab.user.application;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.common.auth.PasswordEncryptor;
import com.flab.user.domain.TokenGenerator;
import com.flab.user.domain.TokenRepository;
import com.flab.user.domain.User;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.InvalidUserException;
import com.flab.user.domain.exception.UserPasswordNotMatchedException;
import java.time.LocalDateTime;


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

    public String execute(LoginCommand command) {
        var user = userRepository.findByEmail(command.getEmail());

        if (user == null) {
            throw new InvalidUserException();
        }

        if (!passwordCheck(command, user)) {
            throw new UserPasswordNotMatchedException();
        }

        var token = tokenGenerator.generate();

        tokenRepository.save(
                new AuthenticatedMember.Builder(user.getId())
                        .setAuthId(token)
                        .setEmail(user.getEmail())
                        .setRole(user.getRole())
                        .setexpireAt(LocalDateTime.now().plusSeconds(tokenExpirationSec))
                        .build()
        );

        return token;
    }

    private boolean passwordCheck(LoginCommand command, User loginUserInfo) {
        return passwordEncryptor.match(command.getPassword(), loginUserInfo.getPassword());
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
