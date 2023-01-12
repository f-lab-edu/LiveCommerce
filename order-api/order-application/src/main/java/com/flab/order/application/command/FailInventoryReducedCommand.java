package com.flab.order.application.command;

import java.time.LocalDateTime;

public final class FailInventoryReducedCommand {

    private final Long orderId;
    private final LocalDateTime occurredOn;

    public FailInventoryReducedCommand(Long orderId, LocalDateTime occurredOn) {
        this.orderId = orderId;
        this.occurredOn = occurredOn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }
}
