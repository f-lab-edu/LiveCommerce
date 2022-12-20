package com.flab.seller.infrastructure.persistence;

import com.flab.common.auth.AuthenticatedSeller;
import com.flab.seller.domain.SessionIdRepository;
import com.flab.seller.infrastructure.persistence.redis.RedisSessionRepository;

public class SessionIdRepositoryAdaptor implements SessionIdRepository {

    private final RedisSessionRepository redisSessionRepository;

    public SessionIdRepositoryAdaptor(RedisSessionRepository redisSessionRepository) {
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
