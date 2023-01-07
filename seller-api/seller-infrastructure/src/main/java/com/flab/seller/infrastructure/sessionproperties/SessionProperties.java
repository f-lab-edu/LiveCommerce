package com.flab.seller.infrastructure.sessionproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security.token")
public class SessionProperties {

    private Long sessionExpirationSec;

    public Long getSessionExpirationSec() {
        return sessionExpirationSec;
    }

    public void setSessionExpirationSec(Long sessionExpirationSec) {
        this.sessionExpirationSec = sessionExpirationSec;
    }
}
