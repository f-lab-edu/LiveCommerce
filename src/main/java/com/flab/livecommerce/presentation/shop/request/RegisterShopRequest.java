package com.flab.livecommerce.presentation.shop.request;

import com.flab.livecommerce.application.shop.command.RegisterShopCommand;

public class RegisterShopRequest {


    public RegisterShopCommand toCommand() {
        return new RegisterShopCommand();
    }
}
