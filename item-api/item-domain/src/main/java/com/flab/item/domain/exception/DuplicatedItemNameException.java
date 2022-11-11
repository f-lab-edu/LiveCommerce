package com.flab.item.domain.exception;

public class DuplicatedItemNameException extends RuntimeException {

    public DuplicatedItemNameException(String message) {
        super(message);
    }
}
