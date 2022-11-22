package com.flab.user.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.response.ErrorCode;

public class InvalidUserException extends BaseException {


    public InvalidUserException() {
        super(ErrorCode.INVALID_USER);
    }

    public InvalidUserException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
