package com.flab.common.exception;

import com.flab.common.response.ErrorCode;

public class InvalidParameterException extends BaseException {

    public InvalidParameterException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public InvalidParameterException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public InvalidParameterException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidParameterException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
