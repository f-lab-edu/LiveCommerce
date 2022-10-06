package com.flab.livecommerce.common.response;

import com.flab.livecommerce.common.exception.BaseException;
import com.flab.livecommerce.common.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    /**
     * http status: 500 AND result: FAIL
     * 정의되지 않은 Exception 상황
     * 시스템 예외 상황. 모니터링 대상
     */
    @ResponseStatus
    @ExceptionHandler(Exception.class)
    public CommonApiResponse onException(Exception e) {
        log.error("error =", e);
        return CommonApiResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
    }

    /**
     * http status: 200 AND result: FAIL
     * 시스템은 이슈 없고, 비즈니스 로직 처리에서 에러가 발생.
     * 확장한 예상 된 예외 발생
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException.class)
    public CommonApiResponse onBaseException(BaseException e) {
        log.warn(
            "cause ={}, errorMsg = {}",
            NestedExceptionUtils.getMostSpecificCause(e),
            NestedExceptionUtils.getMostSpecificCause(e).getMessage()
        );
        return CommonApiResponse.fail(e.getErrorCode().name(), e.getMessage());
    }
}
