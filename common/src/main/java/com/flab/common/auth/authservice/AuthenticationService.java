package com.flab.common.auth.authservice;

public interface AuthenticationService {

    void login(Long memberId);

    void logout();

}
