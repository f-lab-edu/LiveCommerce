package com.flab.inventory.domain.event;


import com.flab.common.domain.DomainEvent;
import com.flab.inventory.domain.PayedItemInfo;
import java.time.LocalDateTime;
import java.util.List;

public final class OrderPayedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final List<PayedItemInfo> payedItemInfos;
    private final LocalDateTime occurredOn;

    public OrderPayedEvent(Long orderId, Long userId, List<PayedItemInfo> payedItemInfos, LocalDateTime occurredOn) {
        this.orderId = orderId;
        this.userId = userId;
        this.payedItemInfos = payedItemInfos;
        this.occurredOn = occurredOn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<PayedItemInfo> getPayedItemInfos() {
        return payedItemInfos;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }
}
