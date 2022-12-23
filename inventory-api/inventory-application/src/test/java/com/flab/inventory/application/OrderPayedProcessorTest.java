package com.flab.inventory.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.Inventory.InventoryState;
import com.flab.inventory.domain.Inventory.SaleStatus;
import com.flab.inventory.domain.ItemQuantity;
import com.flab.inventory.domain.event.OrderPayedEvent;
import com.flab.inventory.domain.exception.NotEnoughQuantityException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class OrderPayedProcessorTest {

    @Test
    @DisplayName("재고가 부족하면 예외가 발생한다.")
    void inventory_reduce_notEnoughQuantity_return_exception() {
        // Arrange
        var inventoryRepository = new FakeInventoryRepository();

        inventoryRepository.save(
            new Inventory(1L, SaleStatus.ON_SALE, "test", 15, InventoryState.INVENTORY_SAFE)
        );
        inventoryRepository.save(
            new Inventory(2L, SaleStatus.ON_SALE, "test2", 100, InventoryState.INVENTORY_SAFE)
        );

        List<ItemQuantity> itemQuantities = List.of(
            new ItemQuantity(1L, 10),
            new ItemQuantity(2L, 10),
            new ItemQuantity(1L, 1),
            new ItemQuantity(1L, 5),
            new ItemQuantity(2L, 99)
        );

        var orderPayedEvent = new OrderPayedEvent(
            1L,
            1L,
            itemQuantities,
            LocalDateTime.now()
        );

        var processor = new OrderPayedProcessor(
            inventoryRepository,
            new DummyApplicationEventPublisher()
        );

        // Act
        Throwable result = catchThrowable(() -> processor.execute(orderPayedEvent));

        // Assert
        assertThat(result.getClass()).isEqualTo(NotEnoughQuantityException.class);
    }


    @Test
    @DisplayName("재고의 여유가 있으면 성공적으로 감소한다.")
    void inventory_reduce_EnoughQuantity() {
        // Arrange
        var inventoryRepository = new FakeInventoryRepository();

        inventoryRepository.save(
            new Inventory(1L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE)
        );
        inventoryRepository.save(
            new Inventory(2L, SaleStatus.ON_SALE, "test2", 100, InventoryState.INVENTORY_SAFE)
        );

        List<ItemQuantity> itemQuantities = List.of(
            new ItemQuantity(1L, 10),
            new ItemQuantity(2L, 10),
            new ItemQuantity(1L, 1),
            new ItemQuantity(1L, 5)
        );

        var orderPayedEvent = new OrderPayedEvent(
            1L,
            1L,
            itemQuantities,
            LocalDateTime.now()
        );

        var processor = new OrderPayedProcessor(
            inventoryRepository,
            new DummyApplicationEventPublisher()
        );

        // Act
        processor.execute(orderPayedEvent);

        // Assert
        assertThat(inventoryRepository.findByItemId(1L).getQuantity()).isEqualTo(14);
        assertThat(inventoryRepository.findByItemId(2L).getQuantity()).isEqualTo(90);
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
