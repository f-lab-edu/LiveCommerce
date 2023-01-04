package com.flab.common.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAggregateRoot {

    private List<DomainEvent> events = new ArrayList<>();

    protected void registerEvent(DomainEvent domainEvent) {
        if (domainEvent == null) {
            throw new IllegalArgumentException("Domain event must not be null");
        }

        this.events.add(domainEvent);
    }

    public List<DomainEvent> pollAllEvents() {
        if (events.isEmpty()) {
            return Collections.emptyList();
        }

        var domainEvents = List.copyOf(events);
        this.events.clear();

        return domainEvents;
    }

}