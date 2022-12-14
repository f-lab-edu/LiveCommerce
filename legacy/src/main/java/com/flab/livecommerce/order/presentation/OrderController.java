package com.flab.livecommerce.order.presentation;

import com.flab.common.response.CommonApiResponse;
import com.flab.livecommerce.order.application.facade.OrderManager;
import com.flab.livecommerce.order.presentation.request.RegisterOrderRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        var order = orderManager.register(request.toCommand());
        return CommonApiResponse.success(order);
    }

    @GetMapping("/{orderId}")
    public CommonApiResponse searchOrder(@PathVariable("orderId") Long id) {
        var orderInfo = orderManager.search(id);

        return CommonApiResponse.success(orderInfo);
    }
}
