package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;
import static com.flab.common.auth.SessionConst.AUTH_STATUS;

import com.flab.common.auth.AuthenticatedSeller;
import com.flab.common.auth.AuthenticatedUser;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.exception.AuthenticationException;
import com.flab.common.exception.AuthorizationException;
import com.flab.common.exception.ErrorCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginCheckInterceptor implements HandlerInterceptor {

    private static Role getMemberRole(HttpServletRequest request) {
        return (Role) request.getAttribute(AUTH_STATUS);
    }

    private void checkUserAuthority(HttpServletRequest request) {
        Role authority = getMemberRole(request);

        if (authority != Role.USER) {
            throw new AuthorizationException(ErrorCode.USER_AUTHORIZATION);
        }

        var authUser = (AuthenticatedUser) request.getAttribute(AUTH_SESSION_MEMBER);

        if (authUser == null) {
            throw new AuthenticationException();
        }

    }

    private void checkSellerAuthority(HttpServletRequest request) {
        Role authority = getMemberRole(request);

        if (authority != Role.SELLER) {
            throw new AuthorizationException(ErrorCode.SELLER_AUTHORIZATION);
        }

        var authSeller = (AuthenticatedSeller) request.getAttribute(AUTH_SESSION_MEMBER);

        if (authSeller == null) {
            throw new AuthenticationException();
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

            default:
                throw new AuthenticationException();
        }

        return true;
    }
}
