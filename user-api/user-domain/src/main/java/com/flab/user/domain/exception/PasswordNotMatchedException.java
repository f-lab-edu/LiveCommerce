package com.flab.user.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.response.ErrorCode;

public class PasswordNotMatchedException extends BaseException {


    public PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    public PasswordNotMatchedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
