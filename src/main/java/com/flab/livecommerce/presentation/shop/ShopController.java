package com.flab.livecommerce.presentation.shop;

import com.flab.livecommerce.application.shop.facade.ShopManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.shop.request.RegisterShopRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/shop")
public class ShopController {

    private final ShopManager shopManager;

    public ShopController(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @PostMapping
    public CommonApiResponse signUp(@RequestBody @Valid RegisterShopRequest request) {
        shopManager.registerPartner(request.toCommand());
        return CommonApiResponse.success(null);
    }

}
