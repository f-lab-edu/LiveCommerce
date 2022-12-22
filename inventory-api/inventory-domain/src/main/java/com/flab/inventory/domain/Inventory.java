package com.flab.inventory.domain;

import com.flab.common.domain.AbstractAggregateRoot;
import com.flab.inventory.domain.event.InventoryReduceFailedEvent;
import com.flab.inventory.domain.event.InventoryReducedEvent;
import com.flab.inventory.domain.exception.NotEnoughQuantity;
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

    public void reduce(Integer count) {
        if (this.quantity >= count) {
            quantity -= count;
            registerEvent(new InventoryReducedEvent(this));
        } else {
            throw new NotEnoughQuantity("재고가 충분하지 않습니다.");
        }
    }

    public void failReduce() {
        registerEvent(new InventoryReduceFailedEvent(this));
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public InventoryState getInventoryState() {
        return inventoryState;
    }
}
