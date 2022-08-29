package com.flab.livecommerce.domain.user.exception;

public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
