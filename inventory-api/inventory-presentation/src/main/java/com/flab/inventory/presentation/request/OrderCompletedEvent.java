package com.flab.inventory.presentation.request;


import static java.util.Comparator.comparing;

import com.flab.common.domain.DomainEvent;
import com.flab.inventory.application.command.OrderCompletedCommand;
import com.flab.inventory.domain.ItemQuantity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public final class OrderCompletedEvent implements DomainEvent {

    private final List<ItemQuantity> itemQuantities;
    private final LocalDateTime occurredOn;

    public OrderCompletedEvent(
        List<ItemQuantity> itemQuantities,
        LocalDateTime occurredOn
    ) {
        this.itemQuantities = itemQuantities;
        this.occurredOn = occurredOn;
    }

    public OrderCompletedCommand toCommand() {
        return new OrderCompletedCommand(sortedItemQuantities());
    }

    private List<ItemQuantity> sortedItemQuantities() {
        return this.itemQuantities.stream()
            .sorted(comparing(ItemQuantity::getItemId))
            .collect(Collectors.toList());
    }

    public List<ItemQuantity> getItemQuantities() {
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
