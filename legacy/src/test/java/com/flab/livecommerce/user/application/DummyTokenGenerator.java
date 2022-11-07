package com.flab.livecommerce.user.application;

import com.flab.livecommerce.user.domain.TokenGenerator;

public class DummyTokenGenerator implements TokenGenerator {

    @Override
    public String generate() {
        return null;
    }
}
