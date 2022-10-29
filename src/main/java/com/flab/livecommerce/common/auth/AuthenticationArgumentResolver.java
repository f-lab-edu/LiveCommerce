package com.flab.livecommerce.common.auth;

import com.flab.livecommerce.common.annotation.Authentication;
import com.flab.livecommerce.common.exception.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasAuthentication = parameter.hasParameterAnnotation(Authentication.class);
        boolean hasAuthenticatedUser = AuthenticatedUser.class
            .isAssignableFrom(parameter.getParameterType());

        return hasAuthentication && hasAuthenticatedUser;
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        var authSession = (AuthenticatedUser) request.getAttribute("authSession");

        if (authSession == null) {
            throw new AuthenticationException();
        }

        return authSession;
    }
}
