package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;
import static com.flab.common.auth.SessionConst.AUTH_STATUS;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.exception.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        LoginCheck loginCheck = ((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class);

        if (loginCheck == null) {
            return true;
        }

        var authMember = (AuthenticatedMember) request.getAttribute(AUTH_SESSION_MEMBER);

        if (authMember == null) {
            throw new AuthenticationException();
        }

        Role.valid(authMember.getRole().name());

        return true;
    }
}
