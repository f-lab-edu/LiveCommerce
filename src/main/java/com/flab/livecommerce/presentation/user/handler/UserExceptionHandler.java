package com.flab.livecommerce.presentation.user.handler;

import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.domain.user.exception.DuplicatedEmailException;
import com.flab.livecommerce.domain.user.exception.InvalidUserException;
import com.flab.livecommerce.domain.user.exception.PasswordNotMatchedException;
import com.flab.livecommerce.domain.user.exception.UnauthorizedUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    //TODO 응답에 빈 "" 무엇으로 할지 논의

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicatedEmailException.class)
    public CommonApiResponse duplicatedEmailException(DuplicatedEmailException e) {
        return CommonApiResponse.fail("", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordNotMatchedException.class)
    public CommonApiResponse passwordNotMatchedException(PasswordNotMatchedException e) {
        return CommonApiResponse.fail("", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidUserException.class)
    public CommonApiResponse invalidUserException(InvalidUserException e) {
        return CommonApiResponse.fail("", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthorizedUserException.class)
    public CommonApiResponse unauthorizedUserException(UnauthorizedUserException e) {
        return CommonApiResponse.fail("", e.getMessage());
    }
}
