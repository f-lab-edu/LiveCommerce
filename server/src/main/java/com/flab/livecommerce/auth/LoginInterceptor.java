package com.flab.livecommerce.auth;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.exception.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (handler instanceof HandlerMethod == false) {
            return true;
        }

        if (((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class) == null) {
            return true;
        }

        AuthenticatedUser authUser = (AuthenticatedUser) request.getAttribute("authSession");

        if (authUser == null) {
            throw new AuthenticationException();
        }

        return true;
    }
}
