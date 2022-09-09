package com.flab.livecommerce.domain.product;


public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);
}
