package com.flab.user.infrastructure.tokenproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security.token")
public final class TokenProperties {

    private Long tokenExpirationSec;

    public Long getTokenExpirationSec() {
        return tokenExpirationSec;
    }

    public void setTokenExpirationSec(Long tokenExpirationSec) {
        this.tokenExpirationSec = tokenExpirationSec;
    }
}
