package com.flab.livecommerce.infrastructure.shop.persistence;

import com.flab.livecommerce.domain.shop.Shop;
import com.flab.livecommerce.domain.shop.ShopRepository;
import com.flab.livecommerce.infrastructure.shop.persistence.jdbctemplate.JdbcTemplateShopRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryAdapter implements ShopRepository {

    private final JdbcTemplateShopRepository shopRepository;

    public ShopRepositoryAdapter(JdbcTemplateShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Shop save(Shop shop) {
        return this.shopRepository.save(shop);
    }

    @Override
    public Shop findById(Long id) {
        return this.shopRepository.findById(id);
    }
}
