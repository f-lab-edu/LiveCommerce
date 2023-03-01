package com.flab.seller.domain.exception;

import com.flab.common.exception.SystemException;
import com.flab.common.exception.ErrorCode;

public class DuplicatedSellerEmailException extends SystemException {

    public DuplicatedSellerEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }

    public DuplicatedSellerEmailException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.DUPLICATED_EMAIL);
    }
}
