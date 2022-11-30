package com.flab.user.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class UserPasswordNotMatchedException extends BaseException {


    public UserPasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    public UserPasswordNotMatchedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
