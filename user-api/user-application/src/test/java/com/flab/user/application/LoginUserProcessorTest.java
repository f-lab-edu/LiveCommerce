package com.flab.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.user.application.command.CreateUserCommand;
import com.flab.user.application.command.LoginUserCommand;
import com.flab.user.application.testdouble.DummyTokenGenerator;
import com.flab.user.application.testdouble.DummyTokenRepository;
import com.flab.user.application.testdouble.FakePasswordEncryptor;
import com.flab.user.application.testdouble.FakeUserRepository;
import com.flab.user.domain.User;
import com.flab.user.domain.exception.UserPasswordNotMatchedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginUserProcessorTest {

    @Test
    @DisplayName("로그인 요청시 저장소에 회원이 없을 경우 예외가 발생한다.")
    void test1() {
        //Arrange
        var userRepository = new FakeUserRepository();
        var sut = new LoginUserProcessor(
            userRepository,
            new DummyTokenGenerator(),
            new DummyTokenRepository(),
            new FakePasswordEncryptor(),
            null
        );

        LoginUserCommand command = createLoginUserCommand("aaa@gmail.com", "123456");

        //Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("로그인 요청시 비밀번호가 틀릴 경우 예외가 발생한다.")
    void test2() {
        //Arrange
        String email = "aaa@gmail.com";
        var userRepository = new FakeUserRepository();
        User user = User.create(email, "12345", "test");
        userRepository.save(user);

        var sut = new LoginUserProcessor(
            userRepository,
            new DummyTokenGenerator(),
            new DummyTokenRepository(),
            new FakePasswordEncryptor(),
            null
        );

        var command = createLoginUserCommand(email, "123456");

        //Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(UserPasswordNotMatchedException.class);
    }

    private LoginUserCommand createLoginUserCommand(String email, String password) {
        return new LoginUserCommand(email, password);
    }
}
