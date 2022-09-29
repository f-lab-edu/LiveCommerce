package com.flab.livecommerce.application.shop;

import com.flab.livecommerce.application.shop.command.RegisterShopCommand;
import com.flab.livecommerce.domain.shop.Shop;
import com.flab.livecommerce.domain.shop.ShopRepository;

public class RegisterShopProcessor {

    private final ShopRepository shopRepository;

    public RegisterShopProcessor(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Shop execute(RegisterShopCommand command) {
        return shopRepository.save(command.toEntity());
    }

}

