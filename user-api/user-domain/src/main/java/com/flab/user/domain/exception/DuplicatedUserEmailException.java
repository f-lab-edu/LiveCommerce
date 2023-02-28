package com.flab.user.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class DuplicatedUserEmailException extends SystemException {

    public DuplicatedUserEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }

    public DuplicatedUserEmailException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.DUPLICATED_EMAIL);
    }
}
