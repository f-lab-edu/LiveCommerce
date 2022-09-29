package com.flab.livecommerce.infrastructure.shop.persistence;

import com.flab.livecommerce.domain.shop.Shop;
import com.flab.livecommerce.domain.shop.ShopRepository;
import com.flab.livecommerce.infrastructure.shop.persistence.jdbctemplate.JdbcTemplateShopRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryAdapter implements ShopRepository {

    private final JdbcTemplateShopRepository partnerRepository;

    public ShopRepositoryAdapter(JdbcTemplateShopRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Shop save(Shop shop) {
        return this.partnerRepository.save(shop);
    }
}
