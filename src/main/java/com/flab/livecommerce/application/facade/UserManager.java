package com.flab.livecommerce.application.facade;

import com.flab.livecommerce.application.UserCreateProcessor;
import com.flab.livecommerce.application.UserCreateProcessor.UserCreateCommand;
import com.flab.livecommerce.application.UserLoginProcessor;
import com.flab.livecommerce.application.UserLoginProcessor.LoginCommand;
import com.flab.livecommerce.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserCreateProcessor userCreateProcessor;
    private final UserLoginProcessor userLoginProcessor;
    private final UserRepository userRepository;

    public UserManager(
            UserCreateProcessor userCreateProcessor,
            UserLoginProcessor userLoginProcessor,
            UserRepository userRepository
    ) {
        this.userCreateProcessor = userCreateProcessor;
        this.userLoginProcessor = userLoginProcessor;
        this.userRepository = userRepository;
    }


    public void createUser(UserCreateCommand command) {
        userCreateProcessor.execute(command);
    }

    public String login(LoginCommand command) {
        return userLoginProcessor.execute(command);
    }

    public void checkEmailDuplicated(String email) {
        if (null != userRepository.findByEmail(email)) {
            throw new IllegalStateException();
        }
    }

}
