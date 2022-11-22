package com.flab.seller.application;


import com.flab.seller.domain.SellerRepository;

public class LoginSellerProcessor {

    private final SellerRepository sellerRepository;

    public LoginSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    //todo login 구현
}
