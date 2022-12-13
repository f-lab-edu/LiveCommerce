package com.flab.seller.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class InvalidSellerException extends BaseException {

    public InvalidSellerException() {
        super(ErrorCode.INVALID_SELLER);
    }

    public InvalidSellerException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
