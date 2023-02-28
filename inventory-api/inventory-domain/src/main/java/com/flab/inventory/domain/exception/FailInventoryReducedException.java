package com.flab.inventory.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class FailInventoryReducedException extends SystemException {

    public FailInventoryReducedException(String message) {
        super(message, ErrorCode.INVENTORY_REDUCE_FAIL);
    }
}
