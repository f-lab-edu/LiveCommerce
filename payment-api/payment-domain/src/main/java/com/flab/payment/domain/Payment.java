package com.flab.payment.domain;

import com.flab.common.domain.AbstractAggregateRoot;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment extends AbstractAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Integer totalAmount;

    public Payment(Long orderId, Integer totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        registerEvent(new PaymentCompletedEvent(this));
    }

    public static Payment create(Long orderId, Integer totalAmount) {
        return new Payment(orderId, totalAmount);
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    protected Payment() {
    }
}
