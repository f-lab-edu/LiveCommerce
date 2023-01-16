package com.flab.point.application;

import com.flab.point.domain.Point;
import com.flab.point.domain.PointRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
class ChargePointProcessorTest {

    @MockBean
    PointRepository pointRepository;

    @InjectMocks
    ChargePointProcessor chargePointProcessor;

    @Test
    @DisplayName("포인트 충전 성공 테스트")
    void chargePoint_complete() {

        // given
        Mockito.when(pointRepository.findByUserId(1L))
                .thenReturn(new Point(1L, 3000));

    }
}