package com.flab.livecommerce.infrastructure.config;

import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.infrastructure.filter.LoginCheckFilter;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
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
