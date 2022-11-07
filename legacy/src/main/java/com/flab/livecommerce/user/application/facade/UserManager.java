package com.flab.livecommerce.user.application.facade;

import com.flab.livecommerce.user.application.UserCreateProcessor;
import com.flab.livecommerce.user.application.UserLoginProcessor;
import com.flab.livecommerce.user.application.UserLoginProcessor.LoginCommand;
import com.flab.livecommerce.user.application.command.UserCreateCommand;
import com.flab.livecommerce.user.domain.TokenRepository;
import com.flab.livecommerce.user.domain.UserRepository;
import com.flab.livecommerce.user.domain.exception.DuplicatedEmailException;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserCreateProcessor userCreateProcessor;
    private final UserLoginProcessor userLoginProcessor;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public UserManager(
        UserCreateProcessor userCreateProcessor,
        UserLoginProcessor userLoginProcessor,
        UserRepository userRepository,
        TokenRepository tokenRepository
    ) {
        this.userCreateProcessor = userCreateProcessor;
        this.userLoginProcessor = userLoginProcessor;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public void createUser(UserCreateCommand command) {
        userCreateProcessor.execute(command);
    }

    public String login(LoginCommand command) {
        return userLoginProcessor.execute(command);
    }

    public void delete(String token) {
        tokenRepository.remove(token);
    }

    public void checkEmailDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedEmailException();
        }
    }

}
