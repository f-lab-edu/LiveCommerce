package com.flab.livecommerce.common.interceptor;

import com.flab.common.exception.AuthenticationException;
import com.flab.livecommerce.user.domain.TokenRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RedisSessionInterceptor implements HandlerInterceptor {

    private final TokenRepository tokenRepository;
    @Value("${expire.default}")
    private long expireTime;

    public RedisSessionInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {

        if (handler instanceof HandlerMethod == false) {
            return true;
        }

        //token 정보 가져오기
        var token = extractToken(request);

        if (token != null) {
            var authenticatedUser = tokenRepository.findByToken(token);

            if (authenticatedUser == null) {
                throw new AuthenticationException();
            }

            tokenRepository.renewExpirationSec(authenticatedUser);

            //request 에 세션정보 담기
            request.setAttribute("authSession", authenticatedUser);
        }

        return true;
    }

    private String extractToken(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null && token.startsWith("Bearer")) {
            return token.replace("Bearer ", "");
        }

        return null;
    }
}
