package com.flab.user.application.facade;

import com.flab.user.application.CreateUserProcessor;
import com.flab.user.application.LoginUserProcessor;
import com.flab.user.application.LoginUserProcessor.LoginCommand;
import com.flab.user.application.LogoutUserProcessor;
import com.flab.user.application.command.CreateUserCommand;
import com.flab.user.application.result.UserResult;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.DuplicatedUserEmailException;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final CreateUserProcessor createUserProcessor;
    private final LoginUserProcessor loginUserProcessor;
    private final LogoutUserProcessor logoutUserProcessor;
    private final UserRepository userRepository;

    public UserManager(
        CreateUserProcessor createUserProcessor,
        LoginUserProcessor loginUserProcessor,
        LogoutUserProcessor logoutUserProcessor,
        UserRepository userRepository
    ) {
        this.createUserProcessor = createUserProcessor;
        this.loginUserProcessor = loginUserProcessor;
        this.logoutUserProcessor = logoutUserProcessor;
        this.userRepository = userRepository;
    }

    public UserResult createUser(CreateUserCommand command) {
        return createUserProcessor.execute(command);
    }

    public String login(LoginCommand command) {
        return loginUserProcessor.execute(command);
    }

    public void logout(String token) {
        logoutUserProcessor.execute(token);
    }

    public void checkEmailDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedUserEmailException();
        }
    }

}
