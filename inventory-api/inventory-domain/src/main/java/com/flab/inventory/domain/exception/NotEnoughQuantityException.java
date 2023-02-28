package com.flab.inventory.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class NotEnoughQuantityException extends SystemException {

    public NotEnoughQuantityException(String message) {
        super(message, ErrorCode.NOT_ENOUGH_QUANTITY);
    }
}
