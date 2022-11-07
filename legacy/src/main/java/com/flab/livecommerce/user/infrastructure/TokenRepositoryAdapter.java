package com.flab.livecommerce.user.infrastructure;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.livecommerce.user.domain.TokenRepository;
import com.flab.livecommerce.user.infrastructure.persistence.redis.RedisTokenRepository;
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
