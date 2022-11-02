package com.flab.livecommerce.common.exception;

import com.flab.livecommerce.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException {


    public EntityNotFoundException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.COMMON_INVALID_PARAMETER);
    }
}
