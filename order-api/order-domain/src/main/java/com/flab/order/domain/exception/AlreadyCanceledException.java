package com.flab.order.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class AlreadyCanceledException extends SystemException {

    public AlreadyCanceledException(String message) {
        super(message, ErrorCode.ALREADY_CANCELED);
    }
}
