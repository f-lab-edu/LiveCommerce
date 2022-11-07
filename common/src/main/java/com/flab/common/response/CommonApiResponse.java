package com.flab.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommonApiResponse<T> {

    private boolean success;
    private T data;
    private Error error;

    public static <T> CommonApiResponse success(T data) {
        return new CommonApiResponse<>(true, data, null);
    }

    public static CommonApiResponse fail(Error error) {
        return new CommonApiResponse<>(false, null, error);
    }

    public static CommonApiResponse fail(ErrorCode errorCode) {
        return new CommonApiResponse(
            false, null, new Error(errorCode.name(), errorCode.getMessage())
        );
    }

    public static CommonApiResponse fail(String code, String message) {
        return new CommonApiResponse<>(false, null, new Error(code, message));
    }

    @AllArgsConstructor
    @Getter
    static class Error {

        //에러 타입
        private String code;
        //에러 메시지
        private String message;
    }
}
