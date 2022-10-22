package com.flab.livecommerce.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.flab.livecommerce.application.user.UserCreateProcessor;
import com.flab.livecommerce.application.user.UserCreateProcessor.UserCreateCommand;
import com.flab.livecommerce.application.user.UserLoginProcessor;
import com.flab.livecommerce.application.user.UserLoginProcessor.LoginCommand;
import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    //todo Adapter 사용하지 않고 테스트 수정해야함, 테스트 책 읽고 수정하기
    //todo ~ 필히 10/24 까지 완료 예정

    UserRepository userRepository;
    TokenRepository tokenRepository;
    PasswordEncryptor encoder;
    TokenGenerator tokenGenerator;
    User user;

    @BeforeEach
    void before() {
    }

    @Test
    void userCreateProcessorTest() {

    }

    @Test
    void userLoginProcessor() {

    }

    @Test
    void createUserTest() {

    }

    @Test
    void bcryptPasswordTest() {

    }

}