package com.flab.seller.infrastructure.persistence;

import com.flab.common.auth.AuthenticatedSeller;
import com.flab.seller.domain.SessionRepository;
import com.flab.seller.infrastructure.persistence.redis.RedisSessionRepository;

public class SessionRepositoryAdaptor implements SessionRepository {

    private final RedisSessionRepository redisSessionRepository;

    public SessionRepositoryAdaptor(RedisSessionRepository redisSessionRepository) {
        this.redisSessionRepository = redisSessionRepository;
    }

    @Override
    public void save(String sessionId, AuthenticatedSeller authenticatedSeller) {
        this.redisSessionRepository.save(sessionId, authenticatedSeller);
    }

    @Override
    public AuthenticatedSeller findBySessionId(String sessionId) {
        return null;
    }

    @Override
    public void renewExpirationSec(AuthenticatedSeller authenticatedSeller) {

    }

    @Override
    public void remove(String sessionId) {

    }
}
