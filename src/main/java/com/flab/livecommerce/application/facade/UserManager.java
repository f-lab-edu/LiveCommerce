package com.flab.livecommerce.application.facade;

import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserCreateProcessor userCreateProcessor;
    private final UserLoginProcessor userLoginProcessor;

    public UserManager(UserCreateProcessor userCreateProcessor,
        UserLoginProcessor userLoginProcessor) {
        this.userCreateProcessor = userCreateProcessor;
        this.userLoginProcessor = userLoginProcessor;
    }

    public void createUser(UserCreateRequest userCreateRequest) {
        userCreateProcessor.execute(userCreateRequest);
    }

    public User login(UserLoginRequest userLoginRequest) {
        return userLoginProcessor.execute(userLoginRequest);
    }

}
