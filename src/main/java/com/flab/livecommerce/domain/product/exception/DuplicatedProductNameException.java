package com.flab.livecommerce.domain.product.exception;

public class DuplicatedProductNameException extends RuntimeException {

    public DuplicatedProductNameException(String message) {
        super(message);
    }
}
