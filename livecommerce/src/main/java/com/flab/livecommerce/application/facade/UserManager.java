package com.flab.livecommerce.application.facade;

import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
    private final UserCreateProcessor userCreateProcessor;

    public UserManager(UserCreateProcessor userCreateProcessor) {
        this.userCreateProcessor = userCreateProcessor;
    }

    public void createUser(UserCreateRequest userCreateRequest) {

        userCreateProcessor.execute(userCreateRequest);
    }
}
