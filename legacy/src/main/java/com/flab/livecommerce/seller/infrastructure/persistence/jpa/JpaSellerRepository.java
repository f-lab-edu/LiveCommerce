package com.flab.seller.infrastructure.persistence.jpa;

import com.flab.seller.domain.Seller;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByBusinessNo(String businessNo);

    boolean existsByName(String name);
}
