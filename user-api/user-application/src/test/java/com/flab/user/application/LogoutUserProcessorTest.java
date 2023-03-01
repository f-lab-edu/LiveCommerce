package com.flab.user.application;


import static org.mockito.Mockito.verify;

import com.flab.user.domain.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LogoutUserProcessorTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private LogoutUserProcessor processor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    @DisplayName("로그아웃시 토큰이 삭제 된다.")
    void logout_success() {
        // Arrange
        String token = "test-token";

        // Act
        processor.execute(token);

        // Assert
        verify(tokenRepository).remove(token);
    }
}