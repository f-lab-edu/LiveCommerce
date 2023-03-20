package com.flab.user.presentation;

import com.flab.common.exception.ErrorCode;
import com.flab.common.response.CommonApiResponse;
import com.flab.user.domain.exception.InvalidTokenException;
import com.flab.user.domain.exception.UserDuplicatedEmailException;
import com.flab.user.domain.exception.UserPasswordNotMatchedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(UserExceptionHandler.class);


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public CommonApiResponse<?> onError(InvalidTokenException e) {
        log.debug("OpeningNotFoundException: {}", e.getMessage());
        return CommonApiResponse.fail(ErrorCode.INVALID_TOKEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDuplicatedEmailException.class)
    public CommonApiResponse<?> onError(UserDuplicatedEmailException e) {
        log.debug("OpeningNotFoundException: {}", e.getMessage());
        return CommonApiResponse.fail(ErrorCode.DUPLICATED_EMAIL);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserPasswordNotMatchedException.class)
    public CommonApiResponse<?> onError(UserPasswordNotMatchedException e) {
        log.debug("OpeningNotFoundException: {}", e.getMessage());
        return CommonApiResponse.fail(ErrorCode.PASSWORD_NOT_MATCHED);
    }
}
