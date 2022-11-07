package com.flab.livecommerce.user.infrastructure.generator;

import com.flab.livecommerce.user.domain.TokenGenerator;
import com.flab.livecommerce.user.domain.TokenRepository;
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
