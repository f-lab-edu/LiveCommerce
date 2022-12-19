package com.flab.seller.application;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;

import javax.servlet.http.HttpSession;

public class LogoutSellerProcessor {

    private final HttpSession session;

    public LogoutSellerProcessor(HttpSession session) {
        this.session = session;
    }

    public void execute() {
        session.removeAttribute(AUTH_SESSION_MEMBER);
    }
}
