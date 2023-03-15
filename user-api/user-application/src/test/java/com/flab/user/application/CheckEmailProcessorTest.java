package com.flab.user.application;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

import com.flab.user.domain.UserRepository;
import com.flab.user.domain.exception.UserDuplicatedEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CheckEmailProcessorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CheckEmailProcessor processor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("이메일 중복시 예외를 반환한다.")
    void email_duplicated() {
        // Arrange
        String email = "audrn@gamil.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        Throwable result = catchThrowable(() -> processor.execute(email));

        // Assert
        assertThat(result.getClass()).isEqualTo(UserDuplicatedEmailException.class);
    }

    @Test
    @DisplayName("이메일 중복이 아니고 성공적으로 수행하는 경우")
    void email_not_duplicated() {
        // Arrange
        String email = "audrn@gamil.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act
        Throwable result = catchThrowable(() -> processor.execute(email));

        // Assert
        assertThat(result).isNull();
    }
}