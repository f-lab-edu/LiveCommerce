package com.flab.livecommerce.seller.application;

import com.flab.livecommerce.seller.application.command.RegisterSellerCommand;
import com.flab.livecommerce.seller.domain.Seller;
import com.flab.livecommerce.seller.domain.SellerRepository;

public class RegisterSellerProcessor {

    private final SellerRepository sellerRepository;

    public RegisterSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller execute(RegisterSellerCommand command) {
        return this.sellerRepository.save(command.toEntity());
    }

}

