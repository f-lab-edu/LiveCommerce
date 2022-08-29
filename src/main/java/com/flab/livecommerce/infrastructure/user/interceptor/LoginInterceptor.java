package com.flab.livecommerce.infrastructure.user.interceptor;

import com.flab.livecommerce.application.user.facade.UserTokenManager;
import com.flab.livecommerce.domain.user.exception.UnauthorizedUserException;
import com.flab.livecommerce.infrastructure.user.annotation.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

    private final UserTokenManager userTokenManager;

    public LoginInterceptor(UserTokenManager userTokenManager) {
        this.userTokenManager = userTokenManager;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            LoginCheck loginCheck = method.getMethodAnnotation(LoginCheck.class);

            if (null == loginCheck) {
                return true;
            }

            String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (hasNoLoginInfo(tokenHeader)) {
                throw new UnauthorizedUserException("유효하지 않은 토큰입니다.");
            }
        }

        return true;
    }

    private boolean hasNoLoginInfo(String tokenHeader) {
        return null == userTokenManager.getLoginUserInfo(tokenHeader.replace("Bearer ", ""));
    }

}
