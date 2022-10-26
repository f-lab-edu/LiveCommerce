package com.flab.livecommerce.application.shop.command;

import com.flab.livecommerce.domain.shop.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterShopCommand {

    private String name;
    private String businessNo;
    private String email;

    public Shop toEntity() {
        return new Shop(name, businessNo, email);
    }
}
