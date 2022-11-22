package com.flab.livecommerce.common.event;

import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher publisher;

    public static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(Object event) {
        if (null != publisher) {
            publisher.publishEvent(event);
        }
    }
}
