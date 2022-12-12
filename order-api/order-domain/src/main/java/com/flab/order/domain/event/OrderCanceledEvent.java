package com.flab.order.domain.event;

import com.flab.common.domain.DomainEvent;
import com.flab.order.domain.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public final class OrderCanceledEvent implements DomainEvent {

    private final Long orderId;
    private final Long accountId;
    private final LocalDateTime occurredOn;

    public OrderCanceledEvent(Order order) {
        this.orderId = order.getId();
        this.accountId = order.getUserId();
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return this.occurredOn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}
