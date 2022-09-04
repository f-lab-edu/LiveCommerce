package com.flab.livecommerce.infrastructure.user.generator;

import com.flab.livecommerce.domain.user.TokenGenerator;
import java.util.UUID;

public class NonInfoTokenGenerator implements TokenGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
