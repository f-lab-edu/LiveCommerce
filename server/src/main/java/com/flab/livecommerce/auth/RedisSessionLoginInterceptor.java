package com.flab.livecommerce.auth;

import com.flab.common.auth.SessionConst;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.exception.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class RedisSessionLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class) == null) {
            return true;
        }

        HttpSession session = request.getSession(false);

        String jSessionId = extractSessionId(request);

        if (jSessionId != null || session != null) {
            if (checkSessionInvalid(session, jSessionId)) {
                throw new AuthenticationException();
            }
        }

        return true;
    }

    private static boolean checkSessionInvalid(HttpSession session, String jSessionId) {
        return session.getAttribute(SessionConst.AUTH_SESSION_MEMBER) == null & session.getAttribute(jSessionId) == null;
    }

    private String extractSessionId(HttpServletRequest request) {
        String jSessionId = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jSessionId != null && jSessionId.startsWith("jSessionId")) {
            return jSessionId.replace("jSessionId ", "");
        }

        return null;
    }
}
