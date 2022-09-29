package com.flab.livecommerce.presentation.shop;

import com.flab.livecommerce.application.shop.facade.ShopManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.shop.request.RegisterShopRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    private final ShopManager shopManager;

    public ShopController(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @PostMapping
    public CommonApiResponse signUp(@RequestBody @Valid RegisterShopRequest request) {
        shopManager.registerShop(request.toCommand());
        return CommonApiResponse.success(null);
    }

    @GetMapping("/{shopId}")
    public CommonApiResponse searchShop(@PathVariable("shopId") Long id) {
        shopManager.searchShop(id);
        return CommonApiResponse.success(null);
    }
}

