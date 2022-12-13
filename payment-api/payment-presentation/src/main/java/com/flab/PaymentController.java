package com.flab;

import com.flab.common.response.CommonApiResponse;
import com.flab.request.PaymentStubRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/payment")
@RestController
public class PaymentController {

    private final PaymentManager paymentManager;

    public PaymentController(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    @PostMapping
    public CommonApiResponse payed(PaymentStubRequest request) {

        paymentManager.payed(request.toCommand());

        return CommonApiResponse.success("ok");
    }
}
