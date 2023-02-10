package com.flab.user.presentation.response;

import com.flab.user.application.result.UserResult;

public final class UserResponse {

    private Long id;
    private String email;

    public UserResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserResponse form(UserResult userResult) {
        return new UserResponse(userResult.getId(), userResult.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
