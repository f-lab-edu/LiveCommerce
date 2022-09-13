package com.flab.livecommerce.application.product.facade;

import com.flab.livecommerce.application.product.ProductAddProcessor;
import com.flab.livecommerce.presentation.product.request.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductManager {

    private final ProductAddProcessor productAddProcessor;

    public ProductManager(ProductAddProcessor productAddProcessor) {
        this.productAddProcessor = productAddProcessor;
    }

    public void addProduct(ProductRequest productRequest) {
        productAddProcessor.execute(productRequest);
    }
}
