package com.flab.user.domain.exception;

import com.flab.common.exception.ErrorCode;
import com.flab.common.exception.SystemException;

public class UserDuplicatedEmailException extends SystemException {

    public UserDuplicatedEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }

    public UserDuplicatedEmailException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.DUPLICATED_EMAIL);
    }
}
