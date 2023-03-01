package com.flab.order.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class AlreadyPayedException extends SystemException {

    public AlreadyPayedException(String message) {
        super(message, ErrorCode.ALREADY_PAYED);
    }
}
