package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;

import com.flab.common.exception.AuthenticationException;
import com.flab.seller.domain.SessionRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;



/*
 * 쿠키 방식이 차단 되어 있어 request header로 session id를 전달받을 경우 작동
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

            //request에 세션정보 담기
            request.setAttribute(AUTH_SESSION_MEMBER, authenticatedSeller);
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
