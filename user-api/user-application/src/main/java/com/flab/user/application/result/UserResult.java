package com.flab.user.application.result;

import com.flab.user.domain.User;

public final class UserResult {

    private Long id;
    private String email;

    private String nickname;

    private UserResult() {
    }

    public UserResult(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserResult from(User user) {
        return new UserResult(user.getId(), user.getEmail(), user.getNickname());
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
