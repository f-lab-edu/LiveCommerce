package com.flab.livecommerce.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.flab.livecommerce.application.UserCreateProcessor.UserCreateCommand;
import com.flab.livecommerce.application.UserLoginProcessor.LoginCommand;
import com.flab.livecommerce.domain.user.PasswordEncryptor;
import com.flab.livecommerce.domain.user.TokenGenerator;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.TokenRepositoryAdapter;
import com.flab.livecommerce.infrastructure.UserRepositoryAdapter;
import com.flab.livecommerce.infrastructure.encryption.SecurityPasswordEncoder;
import com.flab.livecommerce.infrastructure.generator.NonInfoTokenGenerator;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryTokenRepository;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserTest {

    UserRepository userRepository;
    TokenRepository tokenRepository;
    PasswordEncryptor encoder;
    TokenGenerator tokenGenerator;
    User user;

    @BeforeEach
    void before() {
        userRepository = new UserRepositoryAdapter(new InMemoryUserRepository());
        encoder = new SecurityPasswordEncoder(new BCryptPasswordEncoder());
        user = new User(
            "test@gmail.com",
            "test1234",
            "test"
        );
        tokenGenerator = new NonInfoTokenGenerator();
        tokenRepository = new TokenRepositoryAdapter(new InMemoryTokenRepository());
    }

    @Test
    void userCreateProcessorTest() {
        UserCreateProcessor processor = new UserCreateProcessor(userRepository, encoder);

        UserCreateCommand command = new UserCreateCommand(
            user.getEmail(),
            user.getPassword(),
            user.getNickname()
        );

        processor.execute(command);

        User findUser = userRepository.findByEmail(command.getEmail());

        assertThat(findUser.getEmail()).isEqualTo(command.getEmail());
        assertThat(encoder.match(command.getPassword(), findUser.getPassword())).isTrue();
        assertThat(findUser.getNickname()).isEqualTo(command.getNickname());
    }

    @Test
    void userLoginProcessor() {
        //given
        UserLoginProcessor processor = new UserLoginProcessor(userRepository, encoder);
        String id = "sadasd@naver.com";
        String password = "test1234";
        String nickname = "asd";

        User user = new User(
            id,
            encoder.encrypt(password),
            nickname
        );
        userRepository.save(user);

        //when
        LoginCommand command = new LoginCommand("sadasd@naver.com", "test1234");
        User findUser = userRepository.findByEmail(command.getEmail());

        //then
        processor.execute(command);
        assertThat(encoder.match(command.getPassword(), findUser.getPassword())).isTrue();

    }

    @Test
    void createUserTest() {
        String id = "sadasd@naver.com";
        String password = "test1234";
        String nickname = "asd";

        User user = new User(
            id,
            encoder.encrypt(password),
            nickname
        );

        userRepository.save(user);
        User findUser = userRepository.findByEmail(id);

        assertThat(findUser).isNotNull();
        assertThat(encoder.match("test1234", findUser.getPassword())).isTrue();
        assertThat(encoder.match("test4321", findUser.getPassword())).isFalse();
    }

    @Test
    void bcryptPasswordTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("Test1234");

        assertThat(encoder.matches("Test1234", result)).isTrue();
        assertThat(encoder.matches("Test1237", result)).isFalse();
    }

}