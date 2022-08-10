package com.flab.livecommerce.application.facade;

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

    public UserManager(UserCreateProcessor userCreateProcessor,
        UserLoginProcessor userLoginProcessor) {
        this.userCreateProcessor = userCreateProcessor;
        this.userLoginProcessor = userLoginProcessor;
    }

    public void createUser(CreateCommand command) {
        userCreateProcessor.execute(command);
    }

    public User login(LoginCommand command) {
        return userLoginProcessor.execute(command);
    }

}
