package com.flab.livecommerce.application.shop.facade;

import com.flab.livecommerce.application.shop.RegisterShopProcessor;
import com.flab.livecommerce.application.shop.command.RegisterShopCommand;
import com.flab.livecommerce.domain.shop.Shop;
import org.springframework.stereotype.Service;

@Service
public class ShopManager {

    private final RegisterShopProcessor registerShopProcessor;

    public ShopManager(RegisterShopProcessor registerShopProcessor) {
        this.registerShopProcessor = registerShopProcessor;
    }

    public Shop registerPartner(RegisterShopCommand command) {
        return registerShopProcessor.execute(command);
    }
}
