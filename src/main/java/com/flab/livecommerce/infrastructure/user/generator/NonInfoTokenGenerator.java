package com.flab.livecommerce.infrastructure.user.generator;

import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
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
