package com.flab.livecommerce.infrastructure.config;

import com.flab.livecommerce.application.facade.UserTokenManager;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.infrastructure.filter.LoginCheckFilter;
import com.flab.livecommerce.infrastructure.interceptor.LoginInterceptor;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
     * 로그인 인가 - 스프링 인터셉터 사용
     */
    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Bean
    public LoginInterceptor loginInterceptor(
        UserTokenManager userTokenManager
    ) {
        return new LoginInterceptor(userTokenManager);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
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
