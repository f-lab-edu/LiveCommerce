package com.flab.livecommerce.common.interceptor;

import com.flab.livecommerce.common.annotation.LoginCheck;
import com.flab.livecommerce.common.auth.AuthenticatedUser;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.exception.InvalidTokenException;
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

            if (token == null) {
                throw new InvalidTokenException();
            }
            //bearer 토큰 파싱
            if (token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            //token 정보 가져오기
            //todo token 만료시간 갱신하기 분리 필요해 보임
            AuthenticatedUser authenticatedUser = tokenRepository.findByToken(token);

            //request 에 세션정보 담기
            request.setAttribute("authSession", authenticatedUser);
        }

        return true;
    }
}
