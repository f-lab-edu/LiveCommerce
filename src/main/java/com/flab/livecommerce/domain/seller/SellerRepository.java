package com.flab.livecommerce.domain.seller;


public interface SellerRepository {

    Seller save(Seller seller);

    Seller findById(Long id);

    Seller findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    boolean existsByBusinessNo(String businessNo);
}
