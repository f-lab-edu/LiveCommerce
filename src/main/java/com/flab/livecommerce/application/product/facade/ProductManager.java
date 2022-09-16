package com.flab.livecommerce.application.product.facade;

import com.flab.livecommerce.application.product.ProductAddProcessor;
import com.flab.livecommerce.domain.product.ProductRepository;
import com.flab.livecommerce.domain.product.exception.DuplicatedModelNumException;
import com.flab.livecommerce.domain.product.exception.DuplicatedProductNameException;
import com.flab.livecommerce.presentation.product.request.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductManager {

    private final ProductAddProcessor productAddProcessor;
    private final ProductRepository productRepository;

    public ProductManager(
        ProductAddProcessor productAddProcessor,
        ProductRepository productRepository
    ) {
        this.productAddProcessor = productAddProcessor;
        this.productRepository = productRepository;
    }

    public void register(ProductRequest productRequest) {
        productAddProcessor.execute(productRequest);
    }

    public void checkModelNumDuplicated(ProductRequest requestDto) {
        if (null != productRepository.findByModelNumber(requestDto.getModelNumber())) {
            throw new DuplicatedModelNumException("이미 존재하는 모델 번호입니다.");
        }
    }

    public void checkProductNameDuplicated(ProductRequest requestDto) {
        if (null != productRepository.findByName(requestDto.getName())) {
            throw new DuplicatedProductNameException("이미 존재하는 상품명입니다.");
        }
    }
}
