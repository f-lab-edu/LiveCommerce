package com.flab.livecommerce.user.domain.exception;

import com.flab.livecommerce.common.exception.BaseException;
import com.flab.livecommerce.common.response.ErrorCode;

public class DuplicatedEmailException extends BaseException {

    public DuplicatedEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }

    public DuplicatedEmailException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.DUPLICATE_EMAIL);
    }
}
