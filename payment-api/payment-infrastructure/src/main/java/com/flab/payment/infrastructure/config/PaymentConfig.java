package com.flab.payment.infrastructure.config;

import com.flab.payment.application.FakePaymentProcessor;
import com.flab.payment.domain.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public FakePaymentProcessor fakePaymentProcessor(
        PaymentRepository paymentRepository
    ) {
        return new FakePaymentProcessor(paymentRepository);
    }
}
