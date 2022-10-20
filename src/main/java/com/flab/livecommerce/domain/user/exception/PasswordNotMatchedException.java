package com.flab.livecommerce.domain.user.exception;

import com.flab.livecommerce.common.exception.BaseException;
import com.flab.livecommerce.common.response.ErrorCode;

public class PasswordNotMatchedException extends BaseException {


    public PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    public PasswordNotMatchedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
