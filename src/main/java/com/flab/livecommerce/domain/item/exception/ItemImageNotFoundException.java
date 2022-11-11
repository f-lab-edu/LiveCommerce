package com.flab.livecommerce.domain.item.exception;

import com.flab.livecommerce.common.exception.BaseException;
import com.flab.livecommerce.common.response.ErrorCode;

public class ItemImageNotFoundException extends BaseException {

    public ItemImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }

    public ItemImageNotFoundException(String message, ErrorCode errorCode) {
        super(message, ErrorCode.IMAGE_NOT_FOUND);
    }
}
