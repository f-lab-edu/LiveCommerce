package com.flab.point.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class ReducePointException extends BaseException {

    public ReducePointException() {
        super(ErrorCode.NOT_ENOUGH_POINT);
    }
}
