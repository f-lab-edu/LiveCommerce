package com.flab.common.exception;

public class EntityNotFoundException extends SystemException {


    public EntityNotFoundException() {
        super(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
