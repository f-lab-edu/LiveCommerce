package com.flab.order.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.order.domain.Order;
import com.flab.order.domain.OrderItemOption;
import com.flab.order.domain.OrderItemOptionGroup;
import com.flab.order.domain.OrderLineItem;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.event.PaymentCompletedEvent;
import com.flab.order.domain.exception.AlreadyCanceledException;
import com.flab.order.domain.exception.AmountNotMatchedException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentCompletedProcessorTest {

    @Test
    @DisplayName("결제 금액과 주문 금액이 일치하지 않으면 예외가 발생한다")
    void payedAmount_NotMatched_OrderAmount_return_exception() {
        // Arrange
        var processor = new PaymentCompletedProcessor(new OrderRepositoryStub());

        // Act
        Throwable result = catchThrowable(
            () -> processor.execute(new PaymentCompletedEvent(1L, 99001, LocalDateTime.now())));

        // Assert
        assertThat(result.getClass()).isEqualTo(AmountNotMatchedException.class);

    }

    @Test
    @DisplayName("이미 취소된 주문은 결제시 예외가 발생한다.")
    void alreadyOrderCanceled_payed_return_exception() {
        // Arrange
        var processor = new PaymentCompletedProcessor(new OrderRepositoryStub());

        // Act
        Throwable result = catchThrowable(
            () -> processor.execute(new PaymentCompletedEvent(1L, 99000, LocalDateTime.now())));

        // Assert
        assertThat(result.getClass()).isEqualTo(AlreadyCanceledException.class);
    }

    private static final class OrderRepositoryStub implements OrderRepository {

        @Override
        public Order save(Order order) {
            return null;
        }

        @Override
        public Order findById(Long id) {
            Order order = Order.create(1L, "NAVER_PAY",
                List.of(new OrderLineItem(
                        3,
                        1L,
                        "티셔츠",
                        30000,
                        List.of(new OrderItemOptionGroup(
                                1,
                                "사이즈",
                                List.of(new OrderItemOption(
                                        1,
                                        "MEDIUM",
                                        1000
                                    )
                                )
                            ), new OrderItemOptionGroup(
                                2,
                                "컬러",
                                List.of(new OrderItemOption(
                                        1,
                                        "BLACK",
                                        2000
                                    )
                                )
                            )
                        )
                    )
                )
            );

            order.cancel();
            return order;
        }
    }

}
