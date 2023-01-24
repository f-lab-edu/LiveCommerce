package com.flab.inventory.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.flab.inventory.application.command.OpenInventoryCommand;
import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.Inventory.InventoryState;
import com.flab.inventory.domain.Inventory.SaleStatus;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OpenInventoryProcessorTest {

    @Test
    @DisplayName("인벤토리 상품 판매 중으로 변경시 상태가 판매 중으로 바뀐다.")
    void inventory_open_change_sale_state_open() {
        // Arrange
        var inventoryRepository = new FakeInventoryRepository();
        var processor = new OpenInventoryProcessor(inventoryRepository);
        var command = new OpenInventoryCommand(List.of(1L, 2L));

        List<Inventory> inventories = List.of(
            new Inventory(1L, SaleStatus.CLOSE, "test", 30, InventoryState.INVENTORY_SAFE),
            new Inventory(3L, SaleStatus.CLOSE, "test", 30, InventoryState.INVENTORY_SAFE)
        );

        inventoryRepository.saveAll(inventories);

        // Act
        processor.execute(command);

        // Assert
        assertThat(inventories)
            .allSatisfy(
                inventory -> assertThat(inventory.getSaleStatus().equals(SaleStatus.ON_SALE))
            );
    }
}
