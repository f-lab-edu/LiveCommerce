package com.flab.order.infrastructure.feign;

import com.flab.order.domain.ItemQuantity;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-client", url = "http://localhost:8080/api/v1/inventory")
public interface OrderClient {

    @PostMapping(value = "/decrease", produces = "application/json")
    List<ItemQuantity> decrease(@RequestBody List<ItemQuantity> itemQuantities);
}
