package com.flab.livecommerce.infrastructure.user.config;

import com.flab.livecommerce.common.interceptor.LoginInterceptor;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.infrastructure.user.filter.LoginCheckFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
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
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(tokenRepository))
            .order(1)
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
