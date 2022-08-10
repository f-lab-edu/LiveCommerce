package com.flab.livecommerce.application;

import com.flab.livecommerce.application.command.user.CreateCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.encryption.PasswordEncryption;

public class UserCreateProcessor {

    private final UserRepository userRepository;
    private final PasswordEncryption passwordEncryption;

    public UserCreateProcessor(UserRepository userRepository, PasswordEncryption passwordEncryption) {
        this.userRepository = userRepository;
        this.passwordEncryption = passwordEncryption;
    }


    public void execute(CreateCommand command) {

        User findUser = userRepository.findByEmail(command.getEmail());
        //중복 회원 검사
        if (null != findUser) {
            throw new IllegalStateException();
        }


        userRepository.save(command.toUser());
    }
}
