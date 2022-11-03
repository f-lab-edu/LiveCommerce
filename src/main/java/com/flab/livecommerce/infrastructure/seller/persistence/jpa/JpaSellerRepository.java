package com.flab.livecommerce.infrastructure.seller.persistence.jpa;

import com.flab.livecommerce.domain.seller.Seller;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByBusinessNo(String businessNo);

    boolean existsByName(String name);
}
