package com.flab.inventory.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class StockOutException extends SystemException {

    public StockOutException(String message) {
        super(message, ErrorCode.INVENTORY_STOCK_OUT);
    }
}
