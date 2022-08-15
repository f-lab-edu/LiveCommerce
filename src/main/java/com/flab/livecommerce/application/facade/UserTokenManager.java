package com.flab.livecommerce.application.facade;

import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;

public class UserTokenManager {

    private final TokenRepository tokenRepository;
    private final TokenGenerator tokenGenerator;

    public UserTokenManager(
        TokenRepository tokenRepository,
        TokenGenerator tokenGenerator
    ) {
        this.tokenRepository = tokenRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public String save(User user) {
        String token = tokenGenerator.generate();
        tokenRepository.save(token, user);
        return token;
    }
}
