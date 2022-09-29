package com.flab.livecommerce.application.shop.command;

import com.flab.livecommerce.domain.shop.Shop;

public class RegisterShopCommand {

    public Shop toEntity() {
        return new Shop(null, null, null);
    }
}
