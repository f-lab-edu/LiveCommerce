package com.flab.livecommerce.infrastructure.user;

import com.flab.livecommerce.common.AuthenticatedUser;
import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.infrastructure.user.redis.RedisTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryAdapter implements TokenRepository {

    private final RedisTokenRepository tokenRepository;

    public TokenRepositoryAdapter(RedisTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void save(String token, User user) {
        tokenRepository.save(token, user);
    }

    @Override
    public AuthenticatedUser findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void remove(String token) {
        tokenRepository.remove(token);
    }
}
