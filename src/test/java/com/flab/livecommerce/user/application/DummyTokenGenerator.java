package com.flab.livecommerce.user.application;

import com.flab.livecommerce.domain.user.TokenGenerator;

public class DummyTokenGenerator implements TokenGenerator {

    @Override
    public String generate() {
        return null;
    }
}
