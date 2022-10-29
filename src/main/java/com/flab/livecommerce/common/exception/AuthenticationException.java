package com.flab.livecommerce.common.exception;

import com.flab.livecommerce.common.response.ErrorCode;

public class AuthenticationException extends BaseException {

    public AuthenticationException() {
        super(ErrorCode.UN_AUTHENTICATED);
    }

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthenticationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
