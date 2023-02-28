package com.flab.user.application.facade;

import com.flab.user.application.CheckEmailProcessor;
import com.flab.user.application.CreateUserProcessor;
import com.flab.user.application.LoginUserProcessor;
import com.flab.user.application.LogoutUserProcessor;
import com.flab.user.application.command.CreateUserCommand;
import com.flab.user.application.command.LoginUserCommand;
import com.flab.user.application.result.UserResult;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final CreateUserProcessor createUserProcessor;
    private final LoginUserProcessor loginUserProcessor;
    private final LogoutUserProcessor logoutUserProcessor;
    private final CheckEmailProcessor checkEmailProcessor;

    public UserManager(
        CreateUserProcessor createUserProcessor,
        LoginUserProcessor loginUserProcessor,
        LogoutUserProcessor logoutUserProcessor,
        CheckEmailProcessor checkEmailProcessor
    ) {
        this.createUserProcessor = createUserProcessor;
        this.loginUserProcessor = loginUserProcessor;
        this.logoutUserProcessor = logoutUserProcessor;
        this.checkEmailProcessor = checkEmailProcessor;
    }

    public UserResult createUser(CreateUserCommand command) {
        return createUserProcessor.execute(command);
    }

    public String login(LoginUserCommand command) {
        return loginUserProcessor.execute(command);
    }

    public void logout(String token) {
        logoutUserProcessor.execute(token);
    }

    public void checkEmail(String email) {
        checkEmailProcessor.execute(email);
    }
}
