package com.flab.common.exception;

public class AuthorizationException extends BaseException {

    public AuthorizationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthorizationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
