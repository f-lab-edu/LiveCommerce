package com.flab.livecommerce.domain.shop;

import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository {

    Shop save(Shop shop);
}
