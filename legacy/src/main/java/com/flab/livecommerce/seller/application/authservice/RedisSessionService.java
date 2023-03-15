package com.flab.seller.application.authservice;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;
import static com.flab.common.auth.SessionConst.AUTH_STATUS;

import com.flab.common.auth.Role;
import com.flab.common.auth.authservice.AuthenticationService;
import com.flab.seller.domain.SessionRepository;
import javax.servlet.http.HttpSession;


public class RedisSessionService implements AuthenticationService {

    private final SessionRepository sessionRepository;
    private final HttpSession session;
    private final Long sessionExpirationSec;

    public RedisSessionService(
            SessionRepository sessionRepository,
            HttpSession session,
            Long sessionExpirationSec
    ) {
        this.sessionRepository = sessionRepository;
        this.session = session;
        this.sessionExpirationSec = sessionExpirationSec;
    }

    @Override
    public String login(AuthenticatedMember loginSellerInfo) {
        // 세션 정보 생성
        session.setAttribute(SessionConst.AUTH_SESSION_MEMBER, loginSellerInfo);
        session.setAttribute(SessionConst.AUTH_STATUS, Role.SELLER);

        // redis session 정보 저장
        loginSellerInfo.addAuthInfo(session.getId(), sessionExpirationSec);
        sessionRepository.save(session.getId(), loginSellerInfo);

        return loginSellerInfo.getAuthId();
    }

    @Override
    public void logout() {
        sessionRepository.remove(session.getId());
        session.invalidate();
    }
}
