package com.flab.livecommerce.domain.item.exception;

import com.flab.livecommerce.common.exception.BaseException;
import com.flab.livecommerce.common.response.ErrorCode;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException() {
        super(ErrorCode.ITEM_NOT_FOUND);
    }

    public ItemNotFoundException(String message) {
        super(message, ErrorCode.ITEM_NOT_FOUND);
    }
}
