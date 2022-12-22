package com.flab.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.CreateOrderItemOptionCommand;
import com.flab.order.application.command.CreateOrderItemOptionGroupCommand;
import com.flab.order.application.command.CreateOrderLineItemCommand;
import com.flab.order.domain.Order;
import com.flab.order.domain.Order.OrderStatus;
import com.flab.order.domain.OrderRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class CreateOrderProcessorTest {

    @Test
    @DisplayName("주문이 정상적으로 생성된다.")
    void orderCreate_complete() {
        //Arrange
        var processor = new CreateOrderProcessor(
            new DummyOrderRepository(),
            new ApplicationEventPublisherDummy()
        );

        //Act
        var order = processor.execute(1L, createOrderCommand());

        //Assert
        assertThat(order.getTotalAmount()).isEqualTo(103000);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER_CREATED);
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

    private static final class ApplicationEventPublisherDummy implements ApplicationEventPublisher {

        @Override
        public void publishEvent(ApplicationEvent event) {
            ApplicationEventPublisher.super.publishEvent(event);
        }

        @Override
        public void publishEvent(Object event) {

        }
    }
}
