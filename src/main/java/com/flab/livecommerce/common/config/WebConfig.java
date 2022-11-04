package com.flab.livecommerce.common.config;

import com.flab.livecommerce.common.auth.AuthenticationArgumentResolver;
import com.flab.livecommerce.common.filter.LoginCheckFilter;
import com.flab.livecommerce.common.interceptor.LoginInterceptor;
import com.flab.livecommerce.common.interceptor.SessionInterceptor;
import com.flab.livecommerce.user.domain.TokenRepository;
import java.util.List;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
     * 로그인 인가 - 스프링 인터셉터 사용
     */
    private final TokenRepository tokenRepository;

    public WebConfig(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor(tokenRepository))
            .order(1)
            .excludePathPatterns("/error");
        registry.addInterceptor(new LoginInterceptor())
            .order(2)
            .excludePathPatterns("/error");
    }

    //@Bean
    public FilterRegistrationBean logCheckFilter(
        TokenRepository tokenRepository
    ) {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LoginCheckFilter(tokenRepository));
        bean.setOrder(1);
        bean.addUrlPatterns("/*");

        return bean;
    }
}
