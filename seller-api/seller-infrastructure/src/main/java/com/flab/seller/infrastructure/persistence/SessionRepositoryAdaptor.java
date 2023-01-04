package com.flab.seller.infrastructure.persistence;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.seller.domain.SessionRepository;
import com.flab.seller.infrastructure.persistence.redis.RedisSessionRepository;

public class SessionRepositoryAdaptor implements SessionRepository {

    private final RedisSessionRepository redisSessionRepository;

    public SessionRepositoryAdaptor(RedisSessionRepository redisSessionRepository) {
        this.redisSessionRepository = redisSessionRepository;
    }

    @Override
    public void save(String sessionId, AuthenticatedMember authenticatedMember) {
        this.redisSessionRepository.save(sessionId, authenticatedMember);
    }

    @Override
    public AuthenticatedMember findBySessionId(String sessionId) {
        return this.redisSessionRepository.findBySessionId(sessionId);
    }

    @Override
    public void renewExpirationSec(AuthenticatedMember authenticatedMember) {
        this.redisSessionRepository.renewExpirationSec(authenticatedMember);
    }

    @Override
    public void remove(String sessionId) {
        this.redisSessionRepository.remove(sessionId);
    }
}
