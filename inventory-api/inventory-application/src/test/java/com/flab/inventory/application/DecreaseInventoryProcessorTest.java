package com.flab.inventory.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.inventory.application.command.DecreaseInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.Inventory.InventoryState;
import com.flab.inventory.domain.Inventory.SaleStatus;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.data.ItemQuantity;
import com.flab.inventory.domain.exception.NotEnoughQuantityException;
import com.flab.inventory.domain.exception.SalesClosedException;
import com.flab.inventory.domain.exception.StockOutException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecreaseInventoryProcessorTest {

    private final InventoryRepository inventoryRepository = new FakeInventoryRepository();

    @Test
    @DisplayName("재고가 부족하면 예외가 발생한다.")
    void test1() {
        // Arrange
        var sut = new DecreaseInventoryProcessor(inventoryRepository);
        Inventory inventory1 = Inventory.create(1L, SaleStatus.ON_SALE, "test1", 30, InventoryState.INVENTORY_SAFE);
        Inventory inventory2 = Inventory.create(1L, SaleStatus.ON_SALE, "test2", 100, InventoryState.INVENTORY_SAFE);

        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);

        List<ItemQuantity> itemQuantity = List.of(
            createItemQuantity(1L, 40),
            createItemQuantity(2L, 10)
        );

        DecreaseInventoryCommand command = createDecreaseInventoryCommand(itemQuantity);

        // Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        // Assert
        assertThat(result.getClass()).isEqualTo(NotEnoughQuantityException.class);
    }

    @Test
    @DisplayName("재고가 품절 상태이면 예외가 발생한다.")
    void test2() {
        // Arrange
        var processor = new DecreaseInventoryProcessor(inventoryRepository);
        Inventory inventory1 = Inventory.create(1L, SaleStatus.ON_SALE, "test1", 30, InventoryState.INVENTORY_SAFE);
        Inventory inventory2 = Inventory.create(2L, SaleStatus.ON_SALE, "test2", 100, InventoryState.STOCK_OUT);

        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);

        List<ItemQuantity> itemQuantity = List.of(
            createItemQuantity(1L, 10),
            createItemQuantity(2L, 10)
        );

        DecreaseInventoryCommand command = createDecreaseInventoryCommand(itemQuantity);

        // Act
        Throwable result = catchThrowable(() -> processor.execute(command));

        // Assert
        assertThat(result.getClass()).isEqualTo(StockOutException.class);
    }

    @Test
    @DisplayName("판매 종료 상태이면 예외가 발생한다.")
    void test3() {
        // Arrange
        var sut = new DecreaseInventoryProcessor(inventoryRepository);
        Inventory inventory1 = Inventory.create(1L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE);
        Inventory inventory2 = Inventory.create(2L, SaleStatus.CLOSE, "test", 30, InventoryState.INVENTORY_SAFE);

        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);

        List<ItemQuantity> itemQuantity = List.of(
            createItemQuantity(1L, 10),
            createItemQuantity(2L, 10)
        );

        DecreaseInventoryCommand command = createDecreaseInventoryCommand(itemQuantity);

        // Act
        Throwable result = catchThrowable(() -> sut.execute(command));

        // Assert
        assertThat(result.getClass()).isEqualTo(SalesClosedException.class);
    }

    @Test
    @DisplayName("재고의 여유가 있으면 성공적으로 감소한다.")
    void test4() {
        // Arrange
        var sut = new DecreaseInventoryProcessor(inventoryRepository);
        Inventory inventory1 = Inventory.create(1L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE);
        Inventory inventory2 = Inventory.create(2L, SaleStatus.ON_SALE, "test", 30, InventoryState.INVENTORY_SAFE);

        inventoryRepository.save(inventory1);
        inventoryRepository.save(inventory2);

        List<ItemQuantity> itemQuantity = List.of(
            createItemQuantity(1L, 1),
            createItemQuantity(2L, 4),
            createItemQuantity(1L, 3),
            createItemQuantity(2L, 2),
            createItemQuantity(2L, 8)
        );

        DecreaseInventoryCommand command = createDecreaseInventoryCommand(itemQuantity);

        // Act
        sut.execute(command);

        // Assert
        assertThat(inventoryRepository.findByItemId(1L).getQuantity()).isEqualTo(26);
        assertThat(inventoryRepository.findByItemId(2L).getQuantity()).isEqualTo(16);
    }

    private DecreaseInventoryCommand createDecreaseInventoryCommand(List<ItemQuantity> itemQuantities) {
        return new DecreaseInventoryCommand(itemQuantities);
    }

    private ItemQuantity createItemQuantity(Long itemId, Integer count) {
        return new ItemQuantity(itemId, count);
    }
}
