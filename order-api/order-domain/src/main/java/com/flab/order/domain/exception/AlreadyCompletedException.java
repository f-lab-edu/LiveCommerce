package com.flab.order.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class AlreadyCompletedException extends SystemException {

    public AlreadyCompletedException(String message) {
        super(message, ErrorCode.ALREADY_COMPLETED);
    }
}
