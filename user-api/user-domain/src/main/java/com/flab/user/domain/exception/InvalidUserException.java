package com.flab.user.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class InvalidUserException extends SystemException {

    public InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }

    public InvalidUserException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
