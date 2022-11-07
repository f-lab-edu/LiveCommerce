package com.flab.livecommerce.infrastructure.item.config;

import com.flab.livecommerce.common.event.Events;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsConfiguration {

    private final ApplicationContext applicationContext;

    public EventsConfiguration(
        ApplicationContext applicationContext
    ) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public InitializingBean eventsInitializer() {
        return () -> Events.setPublisher(applicationContext);
    }
}
