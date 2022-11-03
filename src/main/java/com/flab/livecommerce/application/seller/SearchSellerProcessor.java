package com.flab.livecommerce.application.seller;

import com.flab.livecommerce.domain.seller.Seller;
import com.flab.livecommerce.domain.seller.SellerRepository;

public class SearchSellerProcessor {

    private final SellerRepository sellerRepository;

    public SearchSellerProcessor(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller execute(Long id) {
        return sellerRepository.findById(id);
    }
}
