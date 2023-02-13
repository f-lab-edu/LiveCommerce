package com.flab.user.application.result;

import com.flab.user.domain.User;

public final class UserResult {
    private Long id;
    private String email;

    public UserResult(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static UserResult form(User user) {
        return new UserResult(user.getId(), user.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
