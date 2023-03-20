package com.flab.livecommerce;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.common.exception.ErrorCode;
import com.flab.common.response.CommonApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonApiResponse<?> onError(HttpMediaTypeNotSupportedException e) {
        log.debug("HttpMediaTypeNotSupportedException: {}", e.getMessage());
        return CommonApiResponse.fail(ErrorCode.COMMON_UNSUPPORTED_MEDIA_TYPE);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonApiResponse<?> onError(HttpRequestMethodNotSupportedException e) {
        log.debug("HttpRequestMethodNotSupportedException: {}", e.getMessage());
        return CommonApiResponse.fail(ErrorCode.COMMON_METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonApiResponse<?> onError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        String message = "Request Error"
            + " " + fieldError.getField()
            + " =" + fieldError.getRejectedValue()
            + " (" + fieldError.getDefaultMessage() + ")";

        return CommonApiResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER.name(), message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public CommonApiResponse<?> onError(BindException e) {
        log.warn(
            "cause ={}, errorMsg = {}",
            NestedExceptionUtils.getMostSpecificCause(e),
            NestedExceptionUtils.getMostSpecificCause(e).getMessage()
        );
        return CommonApiResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public CommonApiResponse<?> onError(EntityNotFoundException e) {
        log.debug("OpeningNotFoundException: {}", e.getMessage());
        return CommonApiResponse.fail(e.getErrorCode().name(), e.getMessage());
    }
}
