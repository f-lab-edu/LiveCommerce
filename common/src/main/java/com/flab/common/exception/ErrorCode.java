package com.flab.common.exception;


public enum ErrorCode {

    // Common Error
    COMMON_SYSTEM_ERROR("오류가 발생했습니다. 잠시 후 다시 시도해주세요"), //장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),
    UN_AUTHENTICATED("인증되지 않은 접근입니다."),
    UNAUTHORIZED_ROLE("현재 유저 권한으로는 접근할 수 없는 리소스 요청입니다."),

    // User, Seller 공통 Error
    DUPLICATED_EMAIL("중복된 이메일 입니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    
    // User Error
    USER_NOT_FOUND("회원을 찾을 수 없습니다."),
    INVALID_USER("유효하지 않은 회원입니다."),
    INVALID_TOKEN("유효하지 않은 인증 토큰입니다."),

    // Seller Error
    SELLER_NOT_FOUND("판매자를 찾을 수 없습니다."),
    INVALID_SELLER("유효하지 않은 판매자입니다."),

    // Point Error
    NOT_ENOUGH_POINT("가용 포인트를 넘어선 사용 요청입니다. 사용 포인트를 조정하거나 포인트를 충전하세요."),
    
    //Order Error
    ALREADY_CANCELED("이미 취소된 주문입니다."),
    ALREADY_PAYED("이미 결제된 주문입니다."),
    ALREADY_COMPLETED("이미 완료된 주문입니다."),
    NOT_MATCHED_AMOUNT("결제 금액과 주문 금액이 일치하지 않습니다."),

    //Inventory Error
    NOT_ENOUGH_QUANTITY("재고 수량이 충분하지 않습니다."),
    INVENTORY_REDUCE_FAIL("재고 감소에 실패했습니다."),
    QUANTITY_CHANGE_FAIL("재고 변경에 실패했습니다."),
    INVENTORY_SALE_CLOSE("판매 종료된 재고입니다."),
    INVENTORY_STOCK_OUT("품절된 재고입니다."),
    INVENTORY_NOT_FOUND("재고를 찾을 수 없습니다.");


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
