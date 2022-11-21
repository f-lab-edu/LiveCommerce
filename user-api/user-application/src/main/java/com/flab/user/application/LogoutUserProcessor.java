package com.flab.user.application;

import com.flab.user.domain.TokenRepository;

public class LogoutUserProcessor {

    private final TokenRepository tokenRepository;

    public LogoutUserProcessor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void execute(String token) {
        tokenRepository.remove(token);
    }
}
