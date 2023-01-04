package com.flab.common.auth.authservice;

import com.flab.common.auth.AuthenticatedMember;

public interface AuthenticationService {

    void login(AuthenticatedMember loginMemberInfo);

    void logout();

}
