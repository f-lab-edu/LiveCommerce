package com.flab.order.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class AlreadyCompletedException extends BaseException {

    public AlreadyCompletedException(String message) {
        super(message, ErrorCode.ALREADY_COMPLETED);
    }
}
