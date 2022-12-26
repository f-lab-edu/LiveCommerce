package com.flab.inventory.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class StockOutException extends BaseException {

    public StockOutException(String message) {
        super(message, ErrorCode.INVENTORY_STOCK_OUT);
    }
}
