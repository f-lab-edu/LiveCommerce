package com.flab.common.auth;

import com.flab.common.exception.AuthorizationException;
import com.flab.common.exception.ErrorCode;
import java.util.Arrays;

public enum Role {
    UNAUTH,
    USER,
    SELLER;

    public static Role valid(String role) {
        return Arrays.stream(Role.values())
            .filter(r -> r.name().equals(role))
            .findFirst()
            .orElseThrow(() -> new AuthorizationException(ErrorCode.UNAUTHORIZED_ROLE));
    }
}
