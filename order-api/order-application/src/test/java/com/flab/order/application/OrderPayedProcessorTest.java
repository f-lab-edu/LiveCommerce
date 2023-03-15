package com.flab.order.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.order.application.command.OrderPayedCommand;
import com.flab.order.domain.DecreaseInventoryService;
import com.flab.order.domain.Order;
import com.flab.order.domain.Order.OrderStatus;
import com.flab.order.domain.OrderItemOption;
import com.flab.order.domain.OrderItemOptionGroup;
import com.flab.order.domain.OrderLineItem;
import com.flab.order.domain.OrderRepository;
import com.flab.order.domain.data.DecreaseInventoryData;
import com.flab.order.domain.data.InventoryData;
import com.flab.order.domain.data.ItemQuantityData;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class OrderPayedProcessorTest {

    private final OrderRepository orderRepository = new FakeOrderRepository();
    private final ApplicationEventPublisher publisher = new DummyApplicationEventPublisher();

    @Test
    @DisplayName("재고감소 서비스 실패시 주문이 취소 된다.")
    void test1() {
        // Arrange
        Order order = orderCreate();
        orderRepository.save(order);
        var dummyItemData = createItemData(null, null);

        var sut = new OrderPayedProcessor(
            new StubFailDecreaseInventoryService(),
            orderRepository,
            publisher
        );
        OrderPayedCommand command = createOrderPayedCommand(1L, List.of(dummyItemData));

        // Act
        sut.execute(command);

        // Assert
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER_CANCELED);
    }

    @Test
    @DisplayName("재고감소 서비스 성공시 주문이 완료 된다.")
    void test2() {
        // Arrange
        Order order = orderCreate();
        orderRepository.save(order);
        var dummyItemData = createItemData(null, null);
        var sut = new OrderPayedProcessor(
            new StubSuccessDecreaseInventoryService(),
            orderRepository,
            publisher
        );
        OrderPayedCommand command = createOrderPayedCommand(1L, List.of(dummyItemData));

        // Act
        sut.execute(command);

        // Assert
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER_COMPLETED);
    }


    private static final class DummyApplicationEventPublisher implements ApplicationEventPublisher {

        @Override
        public void publishEvent(ApplicationEvent event) {

        }

        @Override
        public void publishEvent(Object event) {

        }
    }

    private OrderPayedCommand createOrderPayedCommand(Long orderId, List<ItemQuantityData> itemQuantityData) {
        return new OrderPayedCommand(orderId, itemQuantityData);
    }

    private ItemQuantityData createItemData(Long itemId, Integer count) {
        return new ItemQuantityData(itemId, count);
    }

    private static final class StubFailDecreaseInventoryService implements DecreaseInventoryService {

        @Override
        public DecreaseInventoryData decreaseInventory(List<ItemQuantityData> itemQuantityDataList) {
            return DecreaseInventoryData.fail(Collections.emptyList());
        }
    }

    private static final class StubSuccessDecreaseInventoryService implements DecreaseInventoryService {

        @Override
        public DecreaseInventoryData decreaseInventory(List<ItemQuantityData> itemQuantityDataList) {
            return DecreaseInventoryData.success(List.of(new InventoryData(1L, 10)));
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
