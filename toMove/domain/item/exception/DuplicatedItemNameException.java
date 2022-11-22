package com.flab.livecommerce.domain.item.exception;

import com.flab.livecommerce.common.exception.BaseException;
import com.flab.livecommerce.common.response.ErrorCode;

public class DuplicatedItemNameException extends BaseException {

    public DuplicatedItemNameException() {
        super(ErrorCode.DUPLICATE_ITEM_NAME);
    }

    public DuplicatedItemNameException(String message) {
        super(message, ErrorCode.DUPLICATE_ITEM_NAME);
    }
}
