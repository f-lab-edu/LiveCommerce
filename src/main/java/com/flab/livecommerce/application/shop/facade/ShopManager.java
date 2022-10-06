package com.flab.livecommerce.application.shop.facade;

import com.flab.livecommerce.application.shop.RegisterShopProcessor;
import com.flab.livecommerce.application.shop.SearchShopProcessor;
import com.flab.livecommerce.application.shop.command.RegisterShopCommand;
import com.flab.livecommerce.domain.shop.Shop;
import org.springframework.stereotype.Service;

@Service
public class ShopManager {

    private final RegisterShopProcessor registerShopProcessor;
    private final SearchShopProcessor searchShopProcessor;


    public ShopManager(
        RegisterShopProcessor registerShopProcessor,
        SearchShopProcessor searchShopProcessor
    ) {
        this.registerShopProcessor = registerShopProcessor;
        this.searchShopProcessor = searchShopProcessor;
    }

    public Shop registerShop(RegisterShopCommand command) {
        return registerShopProcessor.execute(command);
    }

    public Shop searchShop(Long id) {
        return searchShopProcessor.execute(id);
    }
}
