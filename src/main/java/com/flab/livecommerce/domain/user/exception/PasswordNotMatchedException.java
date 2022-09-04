package com.flab.livecommerce.domain.user.exception;

public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
