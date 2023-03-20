package com.flab.order.presentation;

import com.flab.common.auth.AuthenticatedUser;
import com.flab.common.auth.Role;
import com.flab.common.auth.annotation.Authentication;
import com.flab.common.auth.annotation.LoginCheck;
import com.flab.common.response.CommonApiResponse;
import com.flab.order.application.facade.OrderManager;
import com.flab.order.presentation.request.CreateOrderRequest;
import com.flab.order.presentation.response.OrderResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderManager orderManager;

    public OrderController(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    @LoginCheck(authority = Role.USER)
    @PostMapping
    public CommonApiResponse<OrderResponse> createOrder(
        @Authentication AuthenticatedUser user,
        @RequestBody @Valid CreateOrderRequest request
    ) {
        var orderResult = orderManager.create(user.getUserId(), request.toCommand());
        return CommonApiResponse.success(OrderResponse.form(orderResult));
    }

    @GetMapping("/{orderId}")
    public CommonApiResponse<OrderResponse> searchOrder(@PathVariable("orderId") Long id) {
        var orderResult = orderManager.search(id);
        return CommonApiResponse.success(OrderResponse.form(orderResult));
    }
}
