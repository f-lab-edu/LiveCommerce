package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;

import com.flab.common.auth.AuthenticatedSeller;
import com.flab.common.auth.AuthenticatedUser;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.exception.AuthenticationException;
import com.flab.common.exception.AuthorizationException;
import com.flab.common.exception.ErrorCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    private void checkUserAuthority(HttpServletRequest request) {
        AuthenticatedUser authUser = (AuthenticatedUser) request.getAttribute(AUTH_SESSION_MEMBER);

        if (authUser == null) {
            throw new AuthenticationException();
        }

        Role authUserRole = authUser.getRole();

        if (authUserRole != Role.USER) {
            throw new AuthorizationException(ErrorCode.USER_AUTHORIZATION);
        }
    }

    private void checkSellerAuthority(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        AuthenticatedSeller authSeller = (AuthenticatedSeller) session.getAttribute(
            AUTH_SESSION_MEMBER);

        if (authSeller == null) {
            throw new AuthenticationException();
        }

        Role authSellerRole = authSeller.getRole();

        if (authSellerRole != Role.SELLER) {
            throw new AuthorizationException(ErrorCode.SELLER_AUTHORIZATION);
        }

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

        LoginCheck loginCheck = ((HandlerMethod) handler).getMethodAnnotation(LoginCheck.class);

        if (loginCheck == null) {
            return true;
        }

        Role authority = loginCheck.authority();

        switch (authority) {
            case USER:
                checkUserAuthority(request);
                break;

            case SELLER:
                checkSellerAuthority(request);
                break;
        }

        return true;
    }
}
