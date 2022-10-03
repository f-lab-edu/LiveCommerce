package com.flab.livecommerce.common.response;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public CommonApiResponse passwordNotMatchedException(InvalidParameterException e) {
        return CommonApiResponse.fail(e.getErrorCode().getMessage(), e.getMessage());
    }
}
