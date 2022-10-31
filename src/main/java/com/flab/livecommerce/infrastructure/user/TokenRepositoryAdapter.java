package com.flab.livecommerce.infrastructure.user;

import com.flab.livecommerce.common.auth.AuthenticatedUser;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.infrastructure.user.persistence.redis.RedisTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryAdapter implements TokenRepository {

    private final RedisTokenRepository tokenRepository;

    public TokenRepositoryAdapter(RedisTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(AuthenticatedUser authenticatedUser) {
        this.tokenRepository.save(authenticatedUser);
    }

    @Override
    public AuthenticatedUser findByToken(String token) {
        return this.tokenRepository.findByToken(token);
    }

    @Override
    public void renewExpirationSec(AuthenticatedUser authenticatedUser) {
        this.tokenRepository.renewExpirationSec(authenticatedUser);
    }

    @Override
    public void remove(String token) {
        this.tokenRepository.remove(token);
    }
}
