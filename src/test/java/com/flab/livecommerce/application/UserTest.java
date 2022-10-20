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
import com.flab.livecommerce.infrastructure.user.TokenRepositoryAdapter;
import com.flab.livecommerce.infrastructure.user.UserRepositoryAdapter;
import com.flab.livecommerce.infrastructure.user.encryption.SecurityPasswordEncoder;
import com.flab.livecommerce.infrastructure.user.generator.NonInfoTokenGenerator;
import com.flab.livecommerce.infrastructure.user.persistence.inmemory.InMemoryUserRepository;
import com.flab.livecommerce.infrastructure.user.persistence.redis.RedisTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserTest {

    //todo Adapter 사용하지 않고 테스트 수정해야함, 테스트 책 읽고 수정하기
    //todo 10/21 (금) 작업 예정

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