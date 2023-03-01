package com.flab.order.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class AmountNotMatchedException extends SystemException {

    public AmountNotMatchedException(String message) {
        super(message, ErrorCode.NOT_MATCHED_AMOUNT);
    }
}
