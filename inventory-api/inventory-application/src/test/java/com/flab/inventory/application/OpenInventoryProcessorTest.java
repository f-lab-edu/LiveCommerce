package com.flab.inventory.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.inventory.application.command.OpenInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.Inventory.InventoryState;
import com.flab.inventory.domain.Inventory.SaleStatus;
import com.flab.inventory.domain.InventoryRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OpenInventoryProcessorTest {

    private final InventoryRepository inventoryRepository = new FakeInventoryRepository();

    @Test
    @DisplayName("인벤토리 상품 판매 중으로 변경시 상태가 판매 중으로 바뀐다.")
    void test1() {
        // Arrange
        var sut = new OpenInventoryProcessor(inventoryRepository);
        var command = createOpenInventoryCommand(List.of(1L, 2L));

        List<Inventory> inventories = List.of(
            Inventory.create(1L, SaleStatus.CLOSE, "test", 30, InventoryState.INVENTORY_SAFE),
            Inventory.create(3L, SaleStatus.CLOSE, "test", 30, InventoryState.INVENTORY_SAFE)
        );

        inventoryRepository.saveAll(inventories);

        // Act
        sut.execute(command);

        // Assert
        assertThat(inventories)
            .allSatisfy(
                inventory -> assertThat(inventory.getSaleStatus().equals(SaleStatus.ON_SALE))
            );
    }

    private OpenInventoryCommand createOpenInventoryCommand(List<Long> ids) {
        return new OpenInventoryCommand(ids);
    }
}
