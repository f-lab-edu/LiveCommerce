package com.flab.livecommerce.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.flab.livecommerce.user.application.UserLoginProcessor.LoginCommand;
import com.flab.livecommerce.user.domain.User;
import com.flab.livecommerce.user.domain.exception.InvalidUserException;
import com.flab.livecommerce.user.domain.exception.PasswordNotMatchedException;
import com.flab.livecommerce.user.infrastructure.token.TokenProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserLoginProcessorTest {


    @Test
    @DisplayName("로그인 요청시 저장소에 회원이 없을 경우 예외가 발생한다.")
    void userLogin_InvalidUserTest() {
        //Arrange
        var userRepository = new FakeUserRepository();
        var processor = new UserLoginProcessor(
            userRepository,
            new DummyTokenGenerator(),
            new DummyTokenRepository(),
            new FakePasswordEncryptor(),
            new TokenProperties() //todo 교체 해야함
        );

        LoginCommand command = new LoginCommand("aaa@gmail.com", "123456");

        //act
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

        var processor = new UserLoginProcessor(
            userRepository,
            new DummyTokenGenerator(),
            new DummyTokenRepository(),
            new FakePasswordEncryptor(),
            new TokenProperties() //todo 교체 해야함
        );

        LoginCommand command = new LoginCommand("aaa@gmail.com", "123456");

        //act
        Throwable result = catchThrowable(() -> processor.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(PasswordNotMatchedException.class);
    }
}
