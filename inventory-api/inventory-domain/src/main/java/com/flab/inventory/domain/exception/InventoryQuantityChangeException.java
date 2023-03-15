package com.flab.inventory.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class InventoryQuantityChangeException extends SystemException {

    public InventoryQuantityChangeException(String message) {
        super(message, ErrorCode.QUANTITY_CHANGE_FAIL);
    }
}
