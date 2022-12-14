package com.flab.livecommerce.auth;

import static com.flab.common.auth.SessionConst.AUTH_SESSION_MEMBER;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.exception.AuthenticationException;
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
        boolean hasAuthenticatedMember = AuthenticatedMember.class
            .isAssignableFrom(parameter.getParameterType());

        return hasAuthentication && hasAuthenticatedMember;
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        var authSession = (AuthenticatedMember) request.getAttribute(AUTH_SESSION_MEMBER);

        if (authSession == null) {
            throw new AuthenticationException();
        }

        return authSession;
    }
}
