package com.flab.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.CreateOrderItemOptionCommand;
import com.flab.order.application.command.CreateOrderItemOptionGroupCommand;
import com.flab.order.application.command.CreateOrderLineItemCommand;
import com.flab.order.application.result.OrderResult;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class CreateOrderProcessorTest {

    private final OrderRepository orderRepository = new DummyOrderRepository();
    private final ApplicationEventPublisher publisher = new DummyApplicationEventPublisher();

    @Test
    @DisplayName("주문이 정상적으로 생성된다.")
    void test1() {
        //Arrange
        var sut = new CreateOrderProcessor(
            orderRepository,
            publisher
        );

        //Act
        OrderResult result = sut.execute(3L, createOrderCommand());

        //Assert
        assertThat(result.getUserId()).isEqualTo(3L);
        assertThat(result.getTotalAmount()).isEqualTo(103000);
    }

    private static final class DummyOrderRepository implements OrderRepository {

        @Override
        public Order save(Order order) {
            return null;
        }

        @Override
        public Order findById(Long id) {
            return null;
        }
    }

    private CreateOrderCommand createOrderCommand() {
        return new CreateOrderCommand(
            "NAVER_PAY",
            List.of(new CreateOrderLineItemCommand(
                    3,
                    1L,
                    "티셔츠",
                    30000,
                    List.of(new CreateOrderItemOptionGroupCommand(
                            1,
                            "사이즈",
                            List.of(new CreateOrderItemOptionCommand(
                                    1,
                                    "MEDIUM",
                                    1000
                                )
                            )
                        ), new CreateOrderItemOptionGroupCommand(
                            2,
                            "컬러",
                            List.of(new CreateOrderItemOptionCommand(
                                    1,
                                    "BLACK",
                                    2000
                                )
                            )
                        )
                    )
                ), new CreateOrderLineItemCommand(
                    1,
                    1L,
                    "양말",
                    2000,
                    List.of(new CreateOrderItemOptionGroupCommand(
                            1,
                            "사이즈",
                            List.of(new CreateOrderItemOptionCommand(
                                    1,
                                    "LARGE",
                                    0
                                )
                            )
                        ), new CreateOrderItemOptionGroupCommand(
                            2,
                            "컬러",
                            List.of(new CreateOrderItemOptionCommand(
                                    1,
                                    "RED",
                                    2000
                                )
                            )
                        )
                    )
                )
            )
        );
    }

    private static final class DummyApplicationEventPublisher implements ApplicationEventPublisher {

        @Override
        public void publishEvent(ApplicationEvent event) {
            ApplicationEventPublisher.super.publishEvent(event);
        }

        @Override
        public void publishEvent(Object event) {

        }
    }
}
