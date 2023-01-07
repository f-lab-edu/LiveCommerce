package com.flab.inventory.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class InventoryQuantityChangeException extends BaseException {

    public InventoryQuantityChangeException(String message) {
        super(message, ErrorCode.QUANTITY_CHANGE_FAIL);
    }
}
