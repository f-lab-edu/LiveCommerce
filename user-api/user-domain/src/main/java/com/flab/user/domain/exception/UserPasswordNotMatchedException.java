package com.flab.user.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class UserPasswordNotMatchedException extends SystemException {

    public UserPasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    public UserPasswordNotMatchedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
