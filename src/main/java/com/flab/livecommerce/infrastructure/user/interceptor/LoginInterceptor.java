package com.flab.livecommerce.infrastructure.user.interceptor;

import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.exception.InvalidTokenException;
import com.flab.livecommerce.infrastructure.user.annotation.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;

    public LoginInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            if (handlerMethod.getMethodAnnotation(LoginCheck.class) == null) {
                return true;
            }

            var token = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (hasNoLoginInfo(token)) {
                throw new InvalidTokenException();
            }
        }

        return true;
    }

    private boolean hasNoLoginInfo(String token) {
        return tokenRepository.findByToken(token.replace("Bearer ", "")) == null;
    }

}
