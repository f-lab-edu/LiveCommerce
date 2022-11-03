package com.flab.livecommerce.infrastructure.seller.persistence;

import com.flab.livecommerce.domain.seller.Seller;
import com.flab.livecommerce.domain.seller.SellerRepository;
import com.flab.livecommerce.infrastructure.seller.persistence.jdbctemplate.JdbcTemplateSellerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryAdapter implements SellerRepository {

    private final JdbcTemplateSellerRepository sellerRepository;

    public SellerRepositoryAdapter(JdbcTemplateSellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        return this.sellerRepository.save(seller);
    }

    @Override
    public Seller findById(Long id) {
        return this.sellerRepository.findById(id);
    }
}
