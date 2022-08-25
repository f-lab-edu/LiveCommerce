package com.flab.livecommerce.infrastructure.filter;

import com.flab.livecommerce.domain.user.TokenRepository;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.PatternMatchUtils;

public class LoginCheckFilter implements Filter {

    private static final String[] denylist = {"/", "/user", "/user/login"};

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

        if (!isLoginCheckPath(httpRequest.getRequestURI())) {
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

    private boolean isLoginCheckPath(String requestUri) {
        return !PatternMatchUtils.simpleMatch(denylist, requestUri);
    }
}
