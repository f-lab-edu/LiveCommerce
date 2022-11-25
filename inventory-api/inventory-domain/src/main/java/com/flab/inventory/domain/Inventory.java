package com.flab.inventory.domain;

import com.flab.common.domain.AbstractAggregateRoot;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory extends AbstractAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    @Enumerated(EnumType.STRING)
    private SaleStatus saleStatus;

    private String itemName;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private InventoryState inventoryState;

    protected Inventory() {
    }

    public Inventory(
        Long itemId,
        SaleStatus saleStatus,
        String itemName,
        Integer quantity,
        InventoryState inventoryState
    ) {
        this.itemId = itemId;
        this.saleStatus = saleStatus;
        this.itemName = itemName;
        this.quantity = quantity;
        this.inventoryState = inventoryState;
    }

    public enum InventoryState {
        INVENTORY_SAFE("재고 양호"),
        STOCK_OUT("품절");

        private final String description;

        InventoryState(String description) {
            this.description = description;
        }
    }

    public enum SaleStatus {
        ON_SALE("판매 중"),
        CLOSE("판매 종료");
        private final String description;

        SaleStatus(String description) {
            this.description = description;
        }
    }
}
