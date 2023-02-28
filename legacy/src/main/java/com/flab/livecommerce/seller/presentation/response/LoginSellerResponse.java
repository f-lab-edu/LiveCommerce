package com.flab.seller.presentation.response;

public class LoginSellerResponse {

    private final String jsessionid;

    public LoginSellerResponse(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getJsessionid() {
        return jsessionid;
    }
}
