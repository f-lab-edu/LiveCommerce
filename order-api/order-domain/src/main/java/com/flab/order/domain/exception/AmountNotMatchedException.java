package com.flab.order.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class AmountNotMatchedException extends BaseException {

    public AmountNotMatchedException(String message) {
        super(message, ErrorCode.NOT_MATCHED_AMOUNT);
    }
}
