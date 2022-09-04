package com.flab.livecommerce.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private Error error;

    public static <T> ApiResponse success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static ApiResponse fail(Error error) {
        return new ApiResponse<>(false, null, error);
    }

    public static ApiResponse fail(String code, String message) {
        return new ApiResponse<>(false, null, new Error(code, message));
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
