package com.flab.livecommerce.domain.shop;


public interface ShopRepository {

    Shop save(Shop shop);

    Shop findById(Long id);
}
