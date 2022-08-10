package com.flab.livecommerce.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private Error error;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(Error error) {
        return new ApiResponse<>(false, null, error);
    }

    public static <T> ApiResponse<T> fail(String code, String type, String message) {
        return new ApiResponse<>(false, null, new Error(code, type, message));
    }

    @AllArgsConstructor
    @Getter
    static class Error {

        private String code;
        private String type;
        private String message;
    }
}
