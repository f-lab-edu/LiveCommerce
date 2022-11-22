package com.flab.seller.presentation;

import com.flab.common.response.CommonApiResponse;
import com.flab.seller.application.facade.SellerManager;
import com.flab.seller.presentation.request.RegisterSellerRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    private final SellerManager sellerManager;

    public SellerController(SellerManager sellerManager) {
        this.sellerManager = sellerManager;
    }

    @PostMapping
    public CommonApiResponse signUp(@RequestBody @Valid RegisterSellerRequest request) {
        //todo 응답 모델 만들어야 함
        //sellerManager.registerSeller(request.toCommand());
        return CommonApiResponse.success(null);
    }

    @GetMapping("/{sellerId}")
    public CommonApiResponse searchSeller(@PathVariable("sellerId") Long id) {
        //sellerManager.searchSeller(id);
        return CommonApiResponse.success(null);
    }
}

