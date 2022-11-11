package com.flab.livecommerce.common.response;


public enum ErrorCode {

    COMMON_SYSTEM_ERROR("오류가 발생했습니다. 잠시 후 다시 시도해주세요"), //장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    IMAGE_NOT_FOUND("이미지가 존재하지 않습니다."),
    DUPLICATE_ITEM_NAME("중복된 상품명입니다."),
    ITEM_NOT_FOUND("상품이 존재하지 않습니다.");


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
