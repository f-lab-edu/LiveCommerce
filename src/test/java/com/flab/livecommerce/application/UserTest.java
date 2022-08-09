package com.flab.livecommerce.application;


import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.UserRepositoryAdapter;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryUserRepository;
import com.flab.livecommerce.presentation.request.UserCreateRequest;
import com.flab.livecommerce.presentation.request.UserLoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserTest {


    UserRepository userRepository = new UserRepositoryAdapter(new InMemoryUserRepository());
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void 회원가입테스트() {
        String Id = "sadasd@naver.com";
        String password = "test1234";
        String nickname = "asd";

        User user = new User(
            Id,
            encoder.encode(password),
            nickname
        );

        userRepository.save(user);
        User findUser = userRepository.findByEmail(Id);

        assertThat(findUser).isNotNull();
        assertThat(encoder.matches("test1234", findUser.getPassword())).isTrue();
        assertThat(encoder.matches("test4321", findUser.getPassword())).isFalse();
    }

    @Test
    void UserCreateProcessorTest() {
        UserCreateProcessor processor = new UserCreateProcessor(userRepository, encoder);
        //given
        String Id = "sadasd@naver.com";
        String password = "test1234";
        String nickname = "asd";

        UserCreateRequest user = new UserCreateRequest(
            Id,
            encoder.encode(password),
            nickname
        );

        processor.execute(user);
        assertThatThrownBy(() -> processor.execute(user))
            .isInstanceOf(IllegalStateException.class);

    }

    @Test
    void UserLoginProcessor() {
        //given
        UserLoginProcessor processor = new UserLoginProcessor(userRepository, encoder);
        String Id = "sadasd@naver.com";
        String password = "test1234";
        String nickname = "asd";

        User user = new User(
            Id,
            encoder.encode(password),
            nickname
        );
        userRepository.save(user);

        //when
        UserLoginRequest loginRequest = new UserLoginRequest("sadasd@naver.com", "test1234");
        User loginUser = processor.execute(loginRequest);

        //then
        assertThat(loginUser.getEmail()).isEqualTo(loginRequest.getEmail());
        assertThat(encoder.matches(loginRequest.getPassword(), loginUser.getPassword())).isTrue();
        assertThat(loginUser.getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    void BCryptPasswordTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("Test1234");

        assertThat(encoder.matches("Test1234", result)).isTrue();
        assertThat(encoder.matches("Test1237", result)).isFalse();
    }
}