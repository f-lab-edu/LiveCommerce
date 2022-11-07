package com.flab.order.presentation;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import javax.validation.Valid;
import com.flab.order.application.facade.OrderManager;
import com.flab.order.presentation.request.CreateOrderRequest;
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

    @LoginCheck
    @PostMapping
    public CommonApiResponse createOrder(
        @Authentication AuthenticatedUser user,
        @RequestBody @Valid CreateOrderRequest request
    ) {
        var order = orderManager.create(1L, request.toCommand());
        return CommonApiResponse.success(order);
    }

    @GetMapping("/{orderId}")
    public CommonApiResponse searchOrder(@PathVariable("orderId") Long id) {
        var orderInfo = orderManager.search(id);

        return CommonApiResponse.success(orderInfo);
    }
}
