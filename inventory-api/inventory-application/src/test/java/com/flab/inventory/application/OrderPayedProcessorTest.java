package com.flab.inventory.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.Inventory.InventoryState;
import com.flab.inventory.domain.Inventory.SaleStatus;
import com.flab.inventory.domain.ItemQuantity;
import com.flab.inventory.domain.event.OrderPayedEvent;
import com.flab.inventory.domain.exception.FailInventoryReducedException;
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
            new Inventory(1L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE)
        );
        inventoryRepository.save(
            new Inventory(2L, SaleStatus.ON_SALE, "test2", 100, InventoryState.INVENTORY_SAFE)
        );

        List<ItemQuantity> itemQuantities = List.of(
            new ItemQuantity(1L, 40),
            new ItemQuantity(2L, 10)
        );

        var orderPayedEvent = new OrderPayedEvent(
            1L,
            1L,
            itemQuantities,
            LocalDateTime.now()
        );

        var processor = new OrderPayedProcessor(inventoryRepository, new DummyApplicationEventPublisher());

        // Act
        Throwable result = catchThrowable(() -> processor.execute(orderPayedEvent));

        // Assert
        assertThat(result.getClass()).isEqualTo(FailInventoryReducedException.class);
    }

    @Test
    @DisplayName("재고가 품절 상태이면 예외가 발생한다.")
    void inventory_reduce_stock_out_return_exception() {
        // Arrange
        var inventoryRepository = new FakeInventoryRepository();


        inventoryRepository.save(
            new Inventory(1L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE)
        );
        inventoryRepository.save(
            new Inventory(2L, SaleStatus.ON_SALE, "test2", 100, InventoryState.STOCK_OUT)
        );

        List<ItemQuantity> itemQuantities = List.of(new ItemQuantity(2L, 10));

        var orderPayedEvent = new OrderPayedEvent(
            1L,
            1L,
            itemQuantities,
            LocalDateTime.now()
        );

        var processor = new OrderPayedProcessor(inventoryRepository, new DummyApplicationEventPublisher());

        // Act
        Throwable result = catchThrowable(() -> processor.execute(orderPayedEvent));

        // Assert
        assertThat(result.getClass()).isEqualTo(FailInventoryReducedException.class);
    }

    @Test
    @DisplayName("재고가 품절 상태이면 예외가 발생한다.")
    void inventory_reduce_close_return_exception() {
        // Arrange
        var inventoryRepository = new FakeInventoryRepository();


        inventoryRepository.save(
            new Inventory(1L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE)
        );
        inventoryRepository.save(
            new Inventory(2L, SaleStatus.CLOSE, "test2", 100, InventoryState.INVENTORY_SAFE)
        );

        List<ItemQuantity> itemQuantities = List.of(new ItemQuantity(2L, 10));

        var orderPayedEvent = new OrderPayedEvent(
            1L,
            1L,
            itemQuantities,
            LocalDateTime.now()
        );

        var processor = new OrderPayedProcessor(inventoryRepository, new DummyApplicationEventPublisher());

        // Act
        Throwable result = catchThrowable(() -> processor.execute(orderPayedEvent));

        // Assert
        assertThat(result.getClass()).isEqualTo(FailInventoryReducedException.class);
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
            new ItemQuantity(1L, 4),
            new ItemQuantity(2L, 2),
            new ItemQuantity(1L, 4),
            new ItemQuantity(2L, 8),
            new ItemQuantity(1L, 4),
            new ItemQuantity(1L, 4)
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
