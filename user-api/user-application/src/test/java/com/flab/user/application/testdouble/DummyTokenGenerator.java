package com.flab.user.application.testdouble;

import com.flab.user.domain.TokenGenerator;

public class DummyTokenGenerator implements TokenGenerator {

    @Override
    public String generate() {
        return null;
    }
}
