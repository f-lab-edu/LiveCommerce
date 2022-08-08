package com.flab.livecommerce.application;

import static org.assertj.core.api.Assertions.*;

import com.flab.livecommerce.domain.user.User;
import com.flab.livecommerce.domain.user.UserRepository;
import com.flab.livecommerce.infrastructure.UserRepositoryAdapter;
import com.flab.livecommerce.infrastructure.persistence.inmemory.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

class UserCreateProcessorTest {


    UserRepository userRepository = new UserRepositoryAdapter(new InMemoryUserRepository());

    @Test
    void execute() {

        User user = new User("sadasd@naver.com", "12122asd", "asdsa");
        User findUser = userRepository.findByEmail(user.getEmail());

        assertThat(findUser).isNull();
    }
}