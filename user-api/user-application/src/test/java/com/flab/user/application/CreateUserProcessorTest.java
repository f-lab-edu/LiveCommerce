package com.flab.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.flab.user.application.command.CreateUserCommand;
import com.flab.user.application.testdouble.FakePasswordEncryptor;
import com.flab.user.application.testdouble.FakeUserRepository;
import com.flab.user.domain.User;
import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.UserDuplicatedEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateUserProcessorTest {

    private final UserRepository userRepository = new FakeUserRepository();

    @Test
    @DisplayName("유저 생성시 이메일이 중복된 경우에 예외가 발생한다.")
    void test1() {
        //Arrange
        String email = "aaa@gamil.com";
        String password = "123456";
        String nickname = "test";

        User user = User.create(email, password, nickname);
        userRepository.save(user);
        CreateUserCommand command = createUserCommand(email, password, nickname);

        var sut = new CreateUserProcessor(userRepository, new FakePasswordEncryptor());

        //Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(UserDuplicatedEmailException.class);
    }

    private CreateUserCommand createUserCommand(String email, String password, String nickname) {
        return new CreateUserCommand(email, password, nickname);
    }
}
