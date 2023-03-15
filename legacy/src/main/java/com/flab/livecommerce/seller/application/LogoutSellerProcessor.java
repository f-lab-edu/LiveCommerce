package com.flab.seller.application;

import com.flab.seller.domain.SessionRepository;
import javax.servlet.http.HttpSession;

public class LogoutSellerProcessor {

    private final HttpSession session;
    private final SessionRepository sessionRepository;

    public LogoutSellerProcessor(
            HttpSession session,
            SessionRepository sessionRepository
    ) {
        this.session = session;
        this.sessionRepository = sessionRepository;
    }

    public void execute() {
        sessionRepository.remove(session.getId());
        session.invalidate();
    }
}
