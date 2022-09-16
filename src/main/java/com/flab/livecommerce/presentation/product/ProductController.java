package com.flab.livecommerce.presentation.product;

import com.flab.livecommerce.application.product.facade.ProductManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.product.request.ProductRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final ProductManager productManager;

    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @PostMapping
    public CommonApiResponse registerProduct(@RequestBody @Valid ProductRequest request) {
        productManager.checkProductNameDuplicated(request);
        productManager.checkModelNumDuplicated(request);
        productManager.register(request);
        return CommonApiResponse.success(null);
    }
}
