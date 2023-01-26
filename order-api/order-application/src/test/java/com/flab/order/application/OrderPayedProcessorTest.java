package com.flab.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.order.application.command.OrderPayedCommand;
import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.Order;
import com.flab.order.domain.Order.OrderStatus;
import com.flab.order.domain.OrderItemOption;
import com.flab.order.domain.OrderItemOptionGroup;
import com.flab.order.domain.OrderLineItem;
import com.flab.order.domain.data.DecreaseInventoryData;
import com.flab.order.domain.data.ItemQuantityData;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class OrderPayedProcessorTest {

    @Test
    @DisplayName("재고감소 서비스 실패시 주문이 취소 된다.")
    void decreaseInventoryService_fail_order_will_be_cancel() {
        // Arrange
        var order = orderCreate();
        var orderRepository = new FakeOrderRepository();
        orderRepository.save(order);

        var processor = new OrderPayedProcessor(
            new FailDecreaseInventoryServiceStub(),
            orderRepository,
            new ApplicationEventPublisherDummy()
        );

        // Act
        processor.execute(new OrderPayedCommand(1L, null));

        // Assert
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER_CANCELED);
    }

    @Test
    @DisplayName("재고감소 서비스 성공시 주문이 완료 된다.")
    void decreaseInventoryService_success_order_will_be_complete() {
        // Arrange
        var order = orderCreate();
        var orderRepository = new FakeOrderRepository();
        orderRepository.save(order);

        var processor = new OrderPayedProcessor(
            new SuccessDecreaseInventoryServiceStub(),
            orderRepository,
            new ApplicationEventPublisherDummy()
        );

        // Act
        processor.execute(new OrderPayedCommand(1L, null));

        // Assert
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER_COMPLETED);
    }


    private static final class ApplicationEventPublisherDummy implements
        ApplicationEventPublisher {

        @Override
        public void publishEvent(ApplicationEvent event) {

        }

        @Override
        public void publishEvent(Object event) {

        }
    }

    private static final class FailDecreaseInventoryServiceStub implements
        DecreaseInventoryService {

        @Override
        public DecreaseInventoryData decreaseInventory(List<ItemQuantityData> itemQuantityDataList) {
            return DecreaseInventoryData.fail(null);
        }
    }

    private static final class SuccessDecreaseInventoryServiceStub implements
        DecreaseInventoryService {

        @Override
        public DecreaseInventoryData decreaseInventory(List<ItemQuantityData> itemQuantityDataList) {
            return DecreaseInventoryData.success(null);
        }
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
