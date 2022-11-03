package com.flab.livecommerce.domain.seller;


public interface SellerRepository {

    Seller save(Seller seller);

    Seller findById(Long id);
}
