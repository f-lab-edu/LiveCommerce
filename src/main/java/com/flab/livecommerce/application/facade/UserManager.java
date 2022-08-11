package com.flab.livecommerce.application.facade;

import com.flab.livecommerce.application.UserCheckProcessor;
import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserCreateProcessor.UserCreateCommand;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.application.UserLoginProcessor.LoginCommand;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserCreateProcessor userCreateProcessor;
    private final UserLoginProcessor userLoginProcessor;
    private final UserCheckProcessor userCheckProcessor;

    public UserManager(
        UserCreateProcessor userCreateProcessor,
        UserLoginProcessor userLoginProcessor,
        UserCheckProcessor userCheckProcessor
    ) {
        this.userCreateProcessor = userCreateProcessor;
        this.userLoginProcessor = userLoginProcessor;
        this.userCheckProcessor = userCheckProcessor;
    }


    public void createUser(UserCreateCommand command) {
        userCreateProcessor.execute(command);
    }

    public void login(LoginCommand command) {
        userLoginProcessor.execute(command);
    }

    public void checkEmailDuplicated(String email) {
        userCheckProcessor.execute(email);
    }

}
