package com.flab.livecommerce.domain.product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);

    Product findByModelNumber(int modelNumber);

    Product findByName(String name);
}
