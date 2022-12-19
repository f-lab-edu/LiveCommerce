package com.flab.seller.domain;


import java.util.Optional;

public interface SellerRepository {

    Seller save(Seller seller);

    Optional<Seller> findById(Long id);

    Optional<Seller> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsByBusinessNo(String businessNo);
}
