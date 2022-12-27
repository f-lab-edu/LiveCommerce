package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;
import static com.flab.common.auth.SessionConst.AUTH_STATUS;

import com.flab.common.auth.Role;
import com.flab.common.exception.AuthenticationException;
import com.flab.seller.domain.SessionRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 보안 혹은 mobile app을 고려한다면 쿠키를 사용하는 경우가 많으므로 request header[Authorization]에서 session id를 받아서 인가 처리
 */
public class RedisSessionIdLoginInterceptor implements HandlerInterceptor {

    private final SessionRepository sessionRepository;

    public RedisSessionIdLoginInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        var sessionId = extractSessionId(request);

        if (sessionId != null) {
            var authenticatedSeller = sessionRepository.findBySessionId(sessionId);

            if (authenticatedSeller == null) {
                throw new AuthenticationException();
            }

            sessionRepository.renewExpirationSec(authenticatedSeller);
            request.setAttribute(AUTH_SESSION_MEMBER, authenticatedSeller);
            request.setAttribute(AUTH_STATUS, Role.SELLER);
        }

        return true;
    }

    private String extractSessionId(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("jsessionid")) {
            return token.replace("jsessionid ", "");
        }

        return null;
    }
}
