package com.flab.common.auth;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuthenticatedSeller implements Serializable {

    private String sessionId;
    private Long sellerId;
    private String businessNo;
    private String email;
    private Role role;
    private LocalDateTime expireAt;

    public AuthenticatedSeller() {
    }

    public AuthenticatedSeller(
            Long sellerId,
            String businessNo,
            String email,
            Role role
    ) {
        this.sellerId = sellerId;
        this.businessNo = businessNo;
        this.email = email;
        this.role = role;
    }

    public void addExpirationSec(long second) {
        this.expireAt = expireAt.plusSeconds(second);
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void addSessionInfo(String sessionId, Long sessionExpirationSec) {
        this.sessionId = sessionId;
        this.expireAt = LocalDateTime.now().plusSeconds(sessionExpirationSec);
    }
}
