package com.flab.inventory.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class SalesClosedException extends BaseException {

    public SalesClosedException(String message) {
        super(message, ErrorCode.INVENTORY_SALE_CLOSE);
    }
}
