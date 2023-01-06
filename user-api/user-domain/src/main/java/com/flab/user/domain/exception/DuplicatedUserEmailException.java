package com.flab.user.domain.exception;


import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class DuplicatedUserEmailException extends BaseException {

    public DuplicatedUserEmailException() {
        super(ErrorCode.DUPLICATED_EMAIL);
    }

    public DuplicatedUserEmailException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.DUPLICATED_EMAIL);
    }
}
