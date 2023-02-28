package com.flab.user.application;

import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.DuplicatedUserEmailException;

public class CheckEmailProcessor {

    private final UserRepository userRepository;

    public CheckEmailProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedUserEmailException();
        }
    }
}
