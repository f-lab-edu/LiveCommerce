package com.flab.seller.presentation.response;

public class LoginSellerResponse {

    private final String jSessionId;

    public LoginSellerResponse(String jSessionId) {
        this.jSessionId = jSessionId;
    }

    public String getjSessionId() {
        return jSessionId;
    }
}
