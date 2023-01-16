package com.flab.point.domain.exception;

import com.flab.common.exception.BaseException;
import com.flab.common.exception.ErrorCode;

public class NotEnoughPointsException extends BaseException {

    public NotEnoughPointsException() {
        super(ErrorCode.NOT_ENOUGH_POINT);
    }
}
