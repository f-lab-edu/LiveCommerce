package com.flab.order.application;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.order.application.command.CreateOrderCommand;
import com.flab.order.application.command.CreateOrderItemOptionCommand;
import com.flab.order.application.command.CreateOrderItemOptionGroupCommand;
import com.flab.order.application.command.CreateOrderLineItemCommand;
import com.flab.order.application.result.OrderResult;
import com.flab.order.domain.Order;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SearchOrderProcessorTest {

    @Test
    @DisplayName("주문을 찾을 수 없는 경우 예외가 발생한다.")
    void searchOrder_NotFound() {
        //Arrange
        var orderRepository = new FakeOrderRepository();

        var processor = new SearchOrderProcessor(orderRepository);

        //Act
        Throwable result = catchThrowable(() -> processor.execute(2L));

        //Assert
        assertThat(result.getClass()).isEqualTo(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("주문을 찾으면 주문을 반환한다.")
    void searchOrder_Found() {
        //Arrange
        var orderRepository = new FakeOrderRepository();
        var order = Order.create(1L, command().getPayMethod(), command().toLineItems());
        order.setId(1L);
        orderRepository.save(order);

        var processor = new SearchOrderProcessor(orderRepository);

        //Act
        OrderResult result = processor.execute(1L);

        //Assert
        assertThat(result.getOrderId()).isEqualTo(order.getId());
        assertThat(result.getUserId()).isEqualTo(order.getUserId());
        assertThat(result.getTotalAmount()).isEqualTo(order.getTotalAmount());
    }

    private CreateOrderCommand command() {
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
}