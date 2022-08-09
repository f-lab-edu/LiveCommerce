package com.flab.livecommerce.infrastructure;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthorization {

    public static final String TOKEN_COOKIE_NAME = "tokenId";

    private Map<String, Object> tokenStorage = new ConcurrentHashMap<>();

    public void createToken(Object userInfo, HttpServletResponse response) {
        String userToken = UUID.randomUUID().toString();
        tokenStorage.put(userToken, userInfo);
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, userToken);

        log.info("token={}", userToken);
        log.info("cookieName={}", TOKEN_COOKIE_NAME);
        response.addCookie(cookie);
    }

    public Object getUserByToken(HttpServletRequest request) {
        Cookie tokenCookie = findCookie(request, TOKEN_COOKIE_NAME);

        if (null == tokenCookie) {
            return new RuntimeException();
        }

        return tokenStorage.get(tokenCookie.getValue());
    }

    private Cookie findCookie(HttpServletRequest request, String tokenCookieName) {
        if (null == request.getCookies()) {
            throw new RuntimeException();
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(tokenCookieName))
                .findAny()
                .orElse(null);
    }

}
