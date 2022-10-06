package com.flab.livecommerce.application.shop;

import com.flab.livecommerce.domain.shop.Shop;
import com.flab.livecommerce.domain.shop.ShopRepository;

public class SearchShopProcessor {

    private final ShopRepository shopRepository;

    public SearchShopProcessor(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Shop execute(Long id) {
        return shopRepository.findById(id);
    }
}
