package com.flab.payment.infrastructure.config;

import com.flab.payment.domain.PaymentRepository;
import com.flab.payment.infrastructure.persistence.PaymentRepositoryAdapter;
import com.flab.payment.infrastructure.persistence.jpa.JpaPaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRepositoryConfig {

    @Bean
    public PaymentRepository paymentRepository(JpaPaymentRepository jpaPaymentRepository) {
        return new PaymentRepositoryAdapter(jpaPaymentRepository);
    }
}
