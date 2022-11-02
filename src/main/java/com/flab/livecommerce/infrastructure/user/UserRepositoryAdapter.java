package com.flab.livecommerce.infrastructure.user;

import com.flab.livecommerce.common.exception.EntityNotFoundException;
import com.flab.livecommerce.common.response.ErrorCode;
import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.user.persistence.jpa.JpaUserRepository;
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
