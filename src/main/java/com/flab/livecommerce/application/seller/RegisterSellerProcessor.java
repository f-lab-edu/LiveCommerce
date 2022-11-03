package com.flab.livecommerce.application.seller;

import com.flab.livecommerce.application.seller.command.RegisterSellerCommand;
import com.flab.livecommerce.domain.seller.Seller;
import com.flab.livecommerce.domain.seller.SellerRepository;

public class RegisterSellerProcessor {

    private final SellerRepository sellerRepository;

    public RegisterSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller execute(RegisterSellerCommand command) {
        return this.sellerRepository.save(command.toEntity());
    }

}

