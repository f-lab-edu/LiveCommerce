package com.flab.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.flab.user.application.LoginUserProcessor.LoginCommand;
import com.flab.user.domain.User;
import com.flab.user.domain.exception.InvalidUserException;
import com.flab.user.domain.exception.PasswordNotMatchedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginUserProcessorTest {

    @Test
    @DisplayName("로그인 요청시 저장소에 회원이 없을 경우 예외가 발생한다.")
    void userLogin_InvalidUserTest() {
        //Arrange
        var userRepository = new FakeUserRepository();
        var processor = new LoginUserProcessor(
            userRepository,
            new DummyTokenGenerator(),
            new DummyTokenRepository(),
            new FakePasswordEncryptor(),
            1000L
        );

        LoginCommand command = new LoginCommand("aaa@gmail.com", "123456");

        //Act
        Throwable result = catchThrowable(() -> processor.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(InvalidUserException.class);
    }

    @Test
    @DisplayName("로그인 요청시 비밀번호가 틀릴 경우 예외가 발생한다.")
    void userLogin_PasswordNotMatchedTest() {
        //Arrange
        var userRepository = new FakeUserRepository();
        userRepository.save(new User("aaa@gmail.com", "12345", "test"));

        var processor = new LoginUserProcessor(
            userRepository,
            new DummyTokenGenerator(),
            new DummyTokenRepository(),
            new FakePasswordEncryptor(),
            1000L
        );

        LoginCommand command = new LoginCommand("aaa@gmail.com", "123456");

        //Act
        Throwable result = catchThrowable(() -> processor.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(PasswordNotMatchedException.class);
    }
}
