package com.flab.order.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.order.application.command.PaymentCompletedCommand;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderItemOption;
import com.flab.order.domain.OrderItemOptionGroup;
import com.flab.order.domain.OrderLineItem;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.exception.AlreadyCanceledException;
import com.flab.order.domain.exception.AlreadyPayedException;
import com.flab.order.domain.exception.AmountNotMatchedException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class PaymentCompletedProcessorTest {

    private final ApplicationEventPublisher publisher = new DummyApplicationEventPublisher();
    private final OrderRepository orderRepository = new StubOrderRepository();

    @Test
    @DisplayName("결제 금액과 주문 금액이 일치하지 않으면 예외가 발생한다")
    void test1() {
        // Arrange
        var sut = new PaymentCompletedProcessor(orderRepository, publisher);
        PaymentCompletedCommand command = createPaymentCompletedCommand(1L, 99001);

        // Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        // Assert
        assertThat(result.getClass()).isEqualTo(AmountNotMatchedException.class);

    }

    @Test
    @DisplayName("이미 취소된 주문은 결제시 예외가 발생한다.")
    void test2() {
        // Arrange
        int payedAmount = 99000;
        var processor = new PaymentCompletedProcessor(orderRepository, publisher);
        PaymentCompletedCommand command = createPaymentCompletedCommand(1L, payedAmount);
        Order order = processor.execute(command);
        order.cancel();

        // Act
        Throwable result = catchThrowable(() -> order.payed(payedAmount));

        // Assert
        assertThat(result.getClass()).isEqualTo(AlreadyCanceledException.class);
    }

    @Test
    @DisplayName("이미 결제된 주문은 결제시 예외가 발생한다.")
    void test3() {
        // Arrange
        int payedAmount = 99000;
        var processor = new PaymentCompletedProcessor(orderRepository, publisher);
        PaymentCompletedCommand command = createPaymentCompletedCommand(1L, payedAmount);
        Order order = processor.execute(command);

        // Act
        Throwable result = catchThrowable(() -> order.payed(payedAmount));

        // Assert
        assertThat(result.getClass()).isEqualTo(AlreadyPayedException.class);
    }


    private static final class DummyApplicationEventPublisher implements ApplicationEventPublisher {

        @Override
        public void publishEvent(ApplicationEvent event) {

        }

        @Override
        public void publishEvent(Object event) {

        }
    }


    private PaymentCompletedCommand createPaymentCompletedCommand(Long orderId, Integer payedAmount) {
        return new PaymentCompletedCommand(orderId, payedAmount);
    }


    private static final class StubOrderRepository implements OrderRepository {

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

            return order;
        }
    }

}
