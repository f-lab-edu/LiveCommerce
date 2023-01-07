package com.flab.user.infrastructure;

import com.flab.common.auth.AuthenticatedMember;
import com.flab.user.domain.TokenRepository;
import com.flab.user.infrastructure.persistence.redis.RedisTokenRepository;

public class TokenRepositoryAdapter implements TokenRepository {

    private final RedisTokenRepository tokenRepository;

    public TokenRepositoryAdapter(RedisTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(AuthenticatedMember authenticatedMember) {
        this.tokenRepository.save(authenticatedMember);
    }

    @Override
    public AuthenticatedMember findByToken(String token) {
        return this.tokenRepository.findByToken(token);
    }

    @Override
    public void renewExpirationSec(AuthenticatedMember authenticatedMember) {
        this.tokenRepository.renewExpirationSec(authenticatedMember);
    }

    @Override
    public void remove(String token) {
        this.tokenRepository.remove(token);
    }
}
