package com.flab.livecommerce.seller.application;

import com.flab.livecommerce.seller.domain.Seller;
import com.flab.livecommerce.seller.domain.SellerRepository;

public class SearchSellerProcessor {

    private final SellerRepository sellerRepository;

    public SearchSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller execute(Long id) {
        return sellerRepository.findById(id);
    }
}
