package com.flab.livecommerce.presentation.order;

import com.flab.livecommerce.application.order.facade.OrderManager;
import com.flab.livecommerce.common.response.CommonApiResponse;
import com.flab.livecommerce.presentation.order.request.RegisterOrderRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/order")
@RestController
public class OrderController {

    private final OrderManager orderManager;

    public OrderController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @PostMapping
    public CommonApiResponse registerOrder(@RequestBody @Valid RegisterOrderRequest request) {
        orderManager.register(request.toCommand());
        return CommonApiResponse.success("ok");
    }
}
