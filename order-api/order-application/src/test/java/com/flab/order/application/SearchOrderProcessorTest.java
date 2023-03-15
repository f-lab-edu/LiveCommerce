package com.flab.order.application;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.order.application.result.OrderResult;
import com.flab.order.domain.Order;
import com.flab.order.domain.OrderItemOption;
import com.flab.order.domain.OrderItemOptionGroup;
import com.flab.order.domain.OrderLineItem;
import com.flab.order.domain.OrderRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SearchOrderProcessorTest {

    private final OrderRepository orderRepository = new FakeOrderRepository();

    @Test
    @DisplayName("주문을 찾을 수 없는 경우 예외가 발생한다.")
    void test1() {
        //Arrange
        var sut = new SearchOrderProcessor(orderRepository);
        Long command = 2L;

        //Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        //Assert
        assertThat(result.getClass()).isEqualTo(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("주문을 찾으면 주문을 반환한다.")
    void test2() {
        //Arrange
        Order order = orderCreate();
        Long command = 1L;
        orderRepository.save(order);

        var sut = new SearchOrderProcessor(orderRepository);

        //Act
        OrderResult result = sut.execute(command);

        //Assert
        assertThat(result.getOrderId()).isEqualTo(order.getId());
        assertThat(result.getUserId()).isEqualTo(order.getUserId());
        assertThat(result.getTotalAmount()).isEqualTo(order.getTotalAmount());
    }

    private Order orderCreate() {
        return Order.create(1L, "NAVER_PAY",
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
    }
}