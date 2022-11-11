package com.flab.livecommerce.user.infrastructure;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.common.response.ErrorCode;
import com.flab.livecommerce.user.domain.User;
import com.flab.livecommerce.user.domain.UserRepository;
import com.flab.livecommerce.user.infrastructure.persistence.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository userRepository;

    public UserRepositoryAdapter(
        JpaUserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
