package com.flab.livecommerce.infrastructure;

import com.flab.livecommerce.domain.user.TokenRepository;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryAdapter implements TokenRepository {

    private final InMemoryTokenRepository inMemoryTokenRepository;

    public TokenRepositoryAdapter(InMemoryTokenRepository inMemoryTokenRepository) {
        this.inMemoryTokenRepository = inMemoryTokenRepository;
    }

    @Override
    public void save(String token, User user) {
        inMemoryTokenRepository.save(token, user);
    }
}
