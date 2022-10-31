package com.flab.livecommerce.common.filter;

import com.flab.livecommerce.domain.user.TokenRepository;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] allowList = {"/", "/user", "/user/login"};

    private final TokenRepository tokenRepository;

    public LoginCheckFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isLoginExceptedPath(httpRequest.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        String tokenHeader = httpRequest.getHeader("Authorization");

        if (null == tokenHeader || !tokenHeader.startsWith("Bearer")) {
            httpResponse.getWriter().write("Test Print");
            return;
        }

        String token = tokenHeader.replace("Bearer ", "");

        if (null == tokenRepository.findByToken(token)) {
            httpResponse.getWriter().write("Test Print");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isLoginExceptedPath(String requestUri) {
        return PatternMatchUtils.simpleMatch(allowList, requestUri);
    }
}
