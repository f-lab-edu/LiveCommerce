package com.flab.order.infrastructure.feign;

import com.flab.order.domain.ItemQuantity;
import com.flab.order.presentation.request.DecreaseInventoryRequest;
import java.util.List;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-client", url = "http://localhost:8080/api/v1/inventory")
public interface OrderFeignClient {

    @PostMapping(value = "/decrease", produces = "application/json")
    void decrease(@RequestBody @Valid DecreaseInventoryRequest request);
}
