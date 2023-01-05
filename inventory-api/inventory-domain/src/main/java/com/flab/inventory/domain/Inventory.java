package com.flab.inventory.domain;

import com.flab.common.domain.AbstractAggregateRoot;
import com.flab.common.exception.InvalidParameterException;
import com.flab.inventory.domain.event.FailInventoryReducedEvent;
import com.flab.inventory.domain.exception.InventoryQuantityChangeException;
import com.flab.inventory.domain.exception.NotEnoughQuantityException;
import com.flab.inventory.domain.exception.SalesClosedException;
import com.flab.inventory.domain.exception.StockOutException;
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

    public void open() {
        if (this.quantity <= 0) {
            throw new NotEnoughQuantityException("재고가 충분하지 않습니다.");
        }

        this.saleStatus = SaleStatus.ON_SALE;
    }

    public void close() {
        this.saleStatus = SaleStatus.CLOSE;
    }

    public void increase(Integer count) {
        this.quantity += count;
        validQuantity();
    }

    public void reduce(Integer count) {
        validReduceCount(count);
        this.quantity -= count;
        validQuantity();
    }

    public void orderReduce(Integer count) {
        validReduceCount(count);
        validInventoryState();
        if (this.quantity >= count) {
            this.quantity -= count;
        } else {
            throw new NotEnoughQuantityException("재고가 충분하지 않습니다.");
        }
        checkQuantity();
    }

    private void validQuantity() {
        if (this.quantity < 0) {
            throw new InventoryQuantityChangeException("재고 수량은 '0 ~ 21억' 사이로 변경할 수 있습니다.");
        }
    }

    private void validReduceCount(Integer count) {
        if (count <= 0) {
            throw new InvalidParameterException("올바르지 않은 수량입니다.");
        }
    }

    private void validInventoryState() {
        if (this.inventoryState == InventoryState.STOCK_OUT) {
            throw new StockOutException("품절 된 상품입니다.");
        }

        if (this.saleStatus == SaleStatus.CLOSE) {
            throw new SalesClosedException("판매 중지된 상품입니다.");
        }
    }

    private void checkQuantity() {
        if (this.quantity == 0) {
            this.inventoryState = InventoryState.STOCK_OUT;
        }
    }

    public void failReduce() {
        registerEvent(new FailInventoryReducedEvent(this));
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

    public void setId(Long id) {
        this.id = id;
    }
}
