package com.flab.livecommerce.presentation.product;

import com.flab.livecommerce.application.product.facade.ProductManager;
import com.flab.livecommerce.common.ApiResponse;
import com.flab.livecommerce.presentation.product.request.ProductRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductManager productManager;

    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @PostMapping
    public ApiResponse addProduct(@RequestBody @Valid ProductRequest requestDto) {
        productManager.checkProductNameDuplicated(requestDto);
        productManager.checkModelNumDuplicated(requestDto);
        productManager.addProduct(requestDto);
        return ApiResponse.success(null);
    }
}
