package com.flab.livecommerce.user.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.response.ErrorCode;

public class InvalidTokenException extends BaseException {


    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }

    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
