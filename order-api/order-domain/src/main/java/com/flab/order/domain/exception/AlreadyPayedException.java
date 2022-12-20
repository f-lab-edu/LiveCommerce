package com.flab.order.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class AlreadyPayedException extends BaseException {

    public AlreadyPayedException(String message) {
        super(message, ErrorCode.ALREADY_PAYED);
    }
}
