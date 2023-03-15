package com.flab.seller.domain.exception;

import com.flab.common.exception.SystemException;
import com.flab.common.exception.ErrorCode;

public class SellerPasswordNotMatchedException extends SystemException {

    public SellerPasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    public SellerPasswordNotMatchedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
