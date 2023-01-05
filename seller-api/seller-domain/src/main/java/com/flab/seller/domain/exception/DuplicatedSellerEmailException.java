package com.flab.seller.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class DuplicatedSellerEmailException extends BaseException {

    public DuplicatedSellerEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }

    public DuplicatedSellerEmailException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.DUPLICATED_EMAIL);
    }
}
