package com.flab.user.infrastructure.generator;

import com.flab.user.domain.TokenGenerator;
import com.flab.user.domain.TokenRepository;
import java.util.UUID;

public class NonInfoTokenGenerator implements TokenGenerator {

    private final TokenRepository tokenRepository;

    public NonInfoTokenGenerator(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
