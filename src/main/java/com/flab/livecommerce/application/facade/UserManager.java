package com.flab.livecommerce.application.facade;

import com.flab.livecommerce.application.UserCheckProcessor;
import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.application.command.user.CreateCommand;
import com.flab.livecommerce.application.command.user.LoginCommand;
import com.flab.livecommerce.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserCreateProcessor userCreateProcessor;
    private final UserLoginProcessor userLoginProcessor;
    private final UserCheckProcessor userCheckProcessor;

    public UserManager(UserCreateProcessor userCreateProcessor,
            UserLoginProcessor userLoginProcessor, UserCheckProcessor userCheckProcessor) {
        this.userCreateProcessor = userCreateProcessor;
        this.userLoginProcessor = userLoginProcessor;
        this.userCheckProcessor = userCheckProcessor;
    }


    public void createUser(CreateCommand command) {
        userCreateProcessor.execute(command);
    }

    public void login(LoginCommand command) {
        userLoginProcessor.execute(command);
    }

    public boolean checkEmailDuplicated(String email) {
        return userCheckProcessor.checkEmail(email);
    }

}
