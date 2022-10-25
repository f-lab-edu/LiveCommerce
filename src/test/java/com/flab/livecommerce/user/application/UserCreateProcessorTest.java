package com.flab.livecommerce.user.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.flab.livecommerce.application.user.UserCreateProcessor;
import com.flab.livecommerce.application.user.UserCreateProcessor.UserCreateCommand;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.exception.DuplicatedEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserCreateProcessorTest {

    @Test
    @DisplayName("이메일이 중복된 경우에 예외가 발생한다.")
    void userCreate_DuplicatedEmailException() {
        //Arrange
        var userRepository = new FakeUserRepository();
        userRepository.save(new User("aaa@gmail.com", "123456", "test"));

        UserCreateProcessor processor = new UserCreateProcessor(
            userRepository,
            new FakePasswordEncryptor()
        );

        UserCreateCommand command = new UserCreateCommand(
            "aaa@gmail.com",
            "123456",
            "test"
        );

        //act
        Throwable result = catchThrowable(() -> processor.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(DuplicatedEmailException.class);
    }
}
