package com.flab.common.exception;

public class AuthenticationException extends SystemException {

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
