package com.flab.point.application;

import com.flab.point.application.command.ReducePointCommand;
import com.flab.point.domain.Point;
import com.flab.point.domain.PointCategory;
import com.flab.point.domain.PointRepository;
import com.flab.point.domain.PointTransaction;
import com.flab.point.domain.PointTransactionRepository;
import com.flab.point.domain.PointTransactionService;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
class ReducePointProcessorTest {

    @MockBean
    PointRepository pointRepository;

    @MockBean
    PointTransactionRepository pointTransactionRepository;

    @MockBean
    PointTransactionService pointTransactionService;

    @InjectMocks
    ReducePointProcessor reducePointProcessor;

    @Test
    @DisplayName("포인트 감소 성공 테스트")
    void reducePoint_complete() {

        // given
        Mockito.when(pointRepository.findByUserId(1L))
                .thenReturn(new Point(1L, 5000));

        Mockito.when(pointTransactionRepository.findByUserIdAndStatus(1L, true))
                .thenReturn(
                        List.of(
                                new PointTransaction(1L, PointCategory.CHARGE, 1500),
                                new PointTransaction(1L, PointCategory.ORDER, 400),
                                new PointTransaction(1L, PointCategory.EVENT, 1100),
                                new PointTransaction(1L, PointCategory.CHARGE, 2000)
                        )
                );
        ReducePointCommand reducePointCommand = new ReducePointCommand(2000);
        Integer remainPoints = reducePointProcessor.execute(1L, reducePointCommand);

        Assertions.assertThat(remainPoints).isEqualTo(3000);


        // ReducePointCommand - reducedAmount 정하기
        // ReducePointProcessor execute() 메서드 호출. (userId, ReducePointCommand)
        // 결과: 원래 포인트 - 감소할 양 = 현재 포인트

        // 현재 포인트보다 더 많은 포인트를 감소하고자 할 경우 -> 에러냄
        //

    }


}