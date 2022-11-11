package com.flab.livecommerce.seller.application;

import com.flab.livecommerce.seller.domain.SellerRepository;

public class LoginSellerProcessor {

    private final SellerRepository sellerRepository;

    public LoginSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    //todo login 구현
}
