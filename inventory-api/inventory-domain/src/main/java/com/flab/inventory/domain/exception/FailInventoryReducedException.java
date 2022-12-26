package com.flab.inventory.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class FailInventoryReducedException extends BaseException {

    public FailInventoryReducedException(String message) {
        super(message, ErrorCode.NOT_ENOUGH_QUANTITY);
    }
}
