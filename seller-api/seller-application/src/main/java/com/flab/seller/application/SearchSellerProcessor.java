package com.flab.seller.application;

import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;

public class SearchSellerProcessor {

    private final SellerRepository sellerRepository;

    public SearchSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller execute(Long id) {
        return sellerRepository.findById(id);
    }
}
