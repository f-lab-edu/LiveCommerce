package com.flab.payment.presentation;

import com.flab.common.response.CommonApiResponse;
import com.flab.payment.application.facade.PaymentManager;
import com.flab.payment.presentation.request.PaymentFakeRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/payments")
@RestController
public class PaymentController {

    private final PaymentManager paymentManager;

    public PaymentController(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    @PostMapping
    public CommonApiResponse payed(@Valid @RequestBody PaymentFakeRequest request) {

        paymentManager.payed(request.toCommand());

        return CommonApiResponse.success("ok");
    }
}
