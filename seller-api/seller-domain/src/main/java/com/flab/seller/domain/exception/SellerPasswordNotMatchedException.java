package com.flab.seller.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class SellerPasswordNotMatchedException extends BaseException {

    public SellerPasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }

    public SellerPasswordNotMatchedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
