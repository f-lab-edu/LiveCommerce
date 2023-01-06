package com.flab.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.flab.user.application.command.CreateUserCommand;
import com.flab.user.domain.User;
import com.flab.user.domain.exception.DuplicatedUserEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateUserProcessorTest {

    @Test
    @DisplayName("이메일이 중복된 경우에 예외가 발생한다.")
    void userCreate_DuplicatedEmailException() {
        //Arrange
        var userRepository = new FakeUserRepository();
        userRepository.save(new User("aaa@gmail.com", "123456", "test"));

        CreateUserProcessor processor = new CreateUserProcessor(
            userRepository,
            new FakePasswordEncryptor()
        );

        CreateUserCommand command = new CreateUserCommand(
            "aaa@gmail.com",
            "123456",
            "test"
        );

        //Act
        Throwable result = catchThrowable(() -> processor.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(DuplicatedUserEmailException.class);
    }
}
