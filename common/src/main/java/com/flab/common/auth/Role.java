package com.flab.common.auth;

import com.flab.common.exception.AuthorizationException;
import com.flab.common.exception.ErrorCode;
import java.util.Arrays;

public enum Role {
    UNAUTH, USER, SELLER;

    public void valid(Role memberRole) { // request
        Role inputRole = Arrays.stream(Role.values())
                .filter(role -> role.equals(memberRole))
                .findFirst()
                .orElse(null);

        if (this != inputRole) {
            throw new AuthorizationException(ErrorCode.UNAUTHORIZED_ROLE);
        }
    }
}
