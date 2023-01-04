package com.flab.common.exception;


public enum ErrorCode {


    COMMON_SYSTEM_ERROR("오류가 발생했습니다. 잠시 후 다시 시도해주세요"), //장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    UN_AUTHENTICATED("인증되지 않은 접근입니다."),

    // User Error
    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    INVALID_USER("유효하지 않은 회원입니다."),
    INVALID_TOKEN("유효하지 않은 인증 토큰입니다."),
    USER_AUTHORIZATION("일반 회원 권한이 없어 해당 리소스에 접근할 수 없습니다."),


    // Seller Error
    SELLER_NOT_FOUND("판매자를 찾을 수 없습니다."),

    INVALID_SELLER("유효하지 않은 판매자입니다."),
    SELLER_AUTHORIZATION("판매자 권한이 없어 해당 리소스에 접근할 수 없습니다."),

    // User, Seller 공통
    DUPLICATED_EMAIL("중복된 이메일 입니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),


    NOT_ENOUGH_POINT("가용 포인트를 넘어선 사용 요청입니다. 사용 포인트를 조정하거나 포인트를 충전하세요.");


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
