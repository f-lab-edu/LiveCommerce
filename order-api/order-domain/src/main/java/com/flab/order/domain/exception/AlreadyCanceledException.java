package com.flab.order.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class AlreadyCanceledException extends BaseException {

    public AlreadyCanceledException(String message) {
        super(message, ErrorCode.ALREADY_CANCELED);
    }
}
