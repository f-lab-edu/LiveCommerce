package com.flab.user.infrastructure.generator;

import com.flab.user.domain.TokenGenerator;
import java.util.UUID;

public class NonInfoTokenGenerator implements TokenGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
