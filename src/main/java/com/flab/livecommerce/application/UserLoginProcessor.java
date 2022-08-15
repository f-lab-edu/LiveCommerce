package com.flab.livecommerce.application;

import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class UserLoginProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryption;
    private final TokenGenerator tokenGenerator;
    private final TokenRepository tokenRepository;

    public UserLoginProcessor(
        UserRepository userRepository,
        PasswordEncryptor passwordEncryption,
        TokenGenerator tokenGenerator,
        TokenRepository tokenRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
        this.tokenGenerator = tokenGenerator;
        this.tokenRepository = tokenRepository;
    }

    public String execute(LoginCommand command) {
        User user = userRepository.findByEmail(command.getEmail());

        if (!idPasswordCheck(command, user)) {
            throw new IllegalStateException();
        }

        //TODO 토큰 만료 LocalDateTime 을 어디에 추가할지, 로그인 요청이 계속 들어오면?
        String token = tokenGenerator.generate();
        tokenRepository.save(token, user);

        return token;
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
