package com.flab.livecommerce.application.user.facade;

import com.flab.livecommerce.application.user.UserCreateProcessor;
import com.flab.livecommerce.application.user.UserCreateProcessor.UserCreateCommand;
import com.flab.livecommerce.application.user.UserLoginProcessor;
import com.flab.livecommerce.application.user.UserLoginProcessor.LoginCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.exception.DuplicatedEmailException;
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

    public User login(LoginCommand command) {
        return userLoginProcessor.execute(command);
    }

    public void checkEmailDuplicated(String email) {
        if (null != userRepository.findByEmail(email)) {
            throw new DuplicatedEmailException("이미 존재하는 이메일 입니다.");
        }
    }

}
