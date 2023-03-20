package com.flab.user.presentation.response;

import com.flab.user.application.result.UserResult;

public final class UserResponse {

    private Long id;
    private String email;
    private String nickname;

    private UserResponse() {
    }

    public UserResponse(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserResponse from(UserResult userResult) {
        return new UserResponse(userResult.getId(), userResult.getEmail(), userResult.getNickname());
    }

    public static String logOut() {
        return "Logout OK";
    }

    public static String checkEmail() {
        return "No duplicate emails";
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
