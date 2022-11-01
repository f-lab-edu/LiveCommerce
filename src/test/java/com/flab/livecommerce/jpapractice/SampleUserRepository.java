package com.flab.livecommerce.jpapractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleUserRepository extends JpaRepository<SampleUser, Long> {

    SampleUser findByName(String name);
}
