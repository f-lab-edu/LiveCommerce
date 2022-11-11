package com.flab.seller.application;

import com.flab.seller.application.command.RegisterSellerCommand;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;

public class RegisterSellerProcessor {

    private final SellerRepository sellerRepository;

    public RegisterSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller execute(RegisterSellerCommand command) {
        return this.sellerRepository.save(command.toEntity());
    }

}

