package com.flab.inventory.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class SalesClosedException extends SystemException {

    public SalesClosedException(String message) {
        super(message, ErrorCode.INVENTORY_SALE_CLOSE);
    }
}
