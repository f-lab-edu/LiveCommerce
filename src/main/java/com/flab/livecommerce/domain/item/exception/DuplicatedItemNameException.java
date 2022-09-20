package com.flab.livecommerce.domain.item.exception;

public class DuplicatedItemNameException extends RuntimeException {

    public DuplicatedItemNameException(String message) {
        super(message);
    }
}
