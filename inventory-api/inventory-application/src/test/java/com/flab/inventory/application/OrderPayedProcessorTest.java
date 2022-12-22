package com.flab.inventory.application;

import static org.assertj.core.api.Assertions.catchThrowable;

import com.flab.inventory.domain.Inventory;
import com.flab.inventory.domain.InventoryRepository;
import com.flab.inventory.domain.PayedItemInfo;
import com.flab.inventory.domain.event.OrderPayedEvent;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderPayedProcessorTest {

    @Test
    @DisplayName("재고가 부족하면 예외가 발생한다.")
    void inventory_reduce_notEnoughQuantity_return_exception() {
        // Arrange
        List<PayedItemInfo> payedItemInfos = Arrays.asList(
            new PayedItemInfo(1L, 10),
            new PayedItemInfo(2L, 10),
            new PayedItemInfo(1L, 1),
            new PayedItemInfo(1L, 5)
        );

        var orderPayedEvent = new OrderPayedEvent(
            1L,
            1L,
            payedItemInfos,
            LocalDateTime.now()
        );

        var processor = new OrderPayedProcessor(new InventoryRepositoryStub());

        // Act
        Throwable result = catchThrowable(
            () -> processor.execute(orderPayedEvent)
        );


        // Assert

    }

    private static final class InventoryRepositoryStub implements InventoryRepository {


        @Override
        public Inventory save(Inventory inventory) {
            return null;
        }

        @Override
        public Inventory findById(Long id) {
            return null;
        }

        @Override
        public Inventory findByItemId(Long id) {
            return null;
        }
    }

}
