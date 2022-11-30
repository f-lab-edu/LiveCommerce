package com.flab.seller.infrastructure.persistence;

import com.flab.common.exception.EntityNotFoundException;
import com.flab.common.exception.ErrorCode;
import com.flab.seller.domain.Seller;
import com.flab.seller.domain.SellerRepository;
import com.flab.seller.infrastructure.persistence.jpa.JpaSellerRepository;


public class SellerRepositoryAdapter implements SellerRepository {

    private final JpaSellerRepository sellerRepository;

    public SellerRepositoryAdapter(JpaSellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        return this.sellerRepository.save(seller);
    }

    @Override
    public Seller findById(Long id) {
        return this.sellerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SELLER_NOT_FOUND));
    }

    @Override
    public Seller findByEmail(String email) {
        return this.sellerRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SELLER_NOT_FOUND));
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.sellerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByName(String name) {
        return this.sellerRepository.existsByName(name);
    }

    @Override
    public boolean existsByBusinessNo(String businessNo) {
        return this.sellerRepository.existsByBusinessNo(businessNo);
    }

}
