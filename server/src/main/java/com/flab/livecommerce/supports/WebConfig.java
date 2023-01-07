package com.flab.livecommerce.supports;

import com.flab.livecommerce.auth.AuthenticationArgumentResolver;
import com.flab.livecommerce.auth.LoginCheckInterceptor;
import com.flab.livecommerce.auth.RedisSessionIdLoginInterceptor;
import com.flab.livecommerce.auth.RedisTokenLoginInterceptor;
import com.flab.seller.domain.SessionRepository;
import com.flab.user.domain.TokenRepository;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TokenRepository tokenRepository;
    private final SessionRepository sessionRepository;

    public WebConfig(
            TokenRepository tokenRepository,
            SessionRepository sessionRepository
    ) {
        this.tokenRepository = tokenRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RedisTokenLoginInterceptor(tokenRepository))
                .order(1)
                .excludePathPatterns("/error");
        registry.addInterceptor(new RedisSessionIdLoginInterceptor(sessionRepository))
                .order(2)
                .excludePathPatterns("/error");
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(3)
                .excludePathPatterns("/error");
    }
}
