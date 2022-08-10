package com.flab.livecommerce.application;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.flab.livecommerce.application.command.user.CreateCommand;
import com.flab.livecommerce.application.command.user.LoginCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.domain.user.encryption.PasswordEncryption;
import com.flab.livecommerce.infrastructure.UserRepositoryAdapter;
import com.flab.livecommerce.infrastructure.encryption.SecurityPasswordEncoder;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserTest {
    /*
    UserRepository userRepository = new UserRepositoryAdapter(new InMemoryUserRepository());
    PasswordEncryption encoder = new SecurityPasswordEncoder();

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
    void userCreateProcessorTest() {
        UserCreateProcessor processor = new UserCreateProcessor(userRepository, encoder);
        //given
        String id = "sadasd@naver.com";
        String password = "test1234";
        String nickname = "asd";

        CreateCommand command = new CreateCommand(
            id,
            encoder.encrypt(password),
            nickname
        );


        processor.execute(command);
        assertThatThrownBy(() -> processor.execute(command))
            .isInstanceOf(IllegalStateException.class);

        User saveUser = userRepository.save(command.toUser());
        User findUser = userRepository.findByEmail(command.toUser().getEmail());

        assertThat(saveUser).isEqualTo(findUser);
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
        User loginUser = processor.execute(command);

        //then
        assertThat(loginUser.getEmail()).isEqualTo(command.getEmail());
        assertThat(encoder.match(command.getPassword(), loginUser.getPassword())).isTrue();
        assertThat(loginUser.getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    void bcryptPasswordTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String result = encoder.encode("Test1234");

        assertThat(encoder.matches("Test1234", result)).isTrue();
        assertThat(encoder.matches("Test1237", result)).isFalse();
    }
    */
}