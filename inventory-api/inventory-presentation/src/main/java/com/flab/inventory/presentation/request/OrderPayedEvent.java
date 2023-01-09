package com.flab.inventory.presentation.request;


import static java.util.Comparator.comparing;

import com.flab.common.domain.DomainEvent;
import com.flab.inventory.application.command.OrderPayedCommand;
import com.flab.inventory.domain.ItemQuantity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public final class OrderPayedEvent implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    //todo 도메인 의존하는데 어떻게 처리해야할지 고민해봐야 함
    private final List<ItemQuantity> itemQuantities;
    private final LocalDateTime occurredOn;

    public OrderPayedEvent(
        Long orderId,
        Long userId,
        List<ItemQuantity> itemQuantities,
        LocalDateTime occurredOn
    ) {
        this.orderId = orderId;
        this.userId = userId;
        this.itemQuantities = itemQuantities;
        this.occurredOn = occurredOn;
    }

    public OrderPayedCommand toCommand() {
        return new OrderPayedCommand(sortedItemQuantities());
    }

    private List<ItemQuantity> sortedItemQuantities() {
        return this.itemQuantities.stream()
            .sorted(comparing(ItemQuantity::getItemId))
            .collect(Collectors.toList());
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<ItemQuantity> getPayedItemInfos() {
        return itemQuantities;
    }

    public LocalDateTime getOccurredOn() {
        return occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return null;
    }
}
