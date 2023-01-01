package com.flab.common.auth;

import com.flab.common.exception.AuthorizationException;
import com.flab.common.exception.ErrorCode;

public enum Role {
    UNAUTH, USER, SELLER;

    public void valid(Role memberRole) {
        if (this == Role.USER && this != memberRole) {
            throw new AuthorizationException(ErrorCode.USER_AUTHORIZATION);
        }
        if (this == Role.SELLER && this != memberRole) {
            throw new AuthorizationException(ErrorCode.SELLER_AUTHORIZATION);
        }
    }

}
