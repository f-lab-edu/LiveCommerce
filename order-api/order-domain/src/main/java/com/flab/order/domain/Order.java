package com.flab.order.domain;

import com.flab.common.domain.AbstractAggregateRoot;
import com.flab.order.domain.event.OrderCanceledEvent;
import com.flab.order.domain.event.OrderCompletedEvent;
import com.flab.order.domain.event.OrderCreatedEvent;
import com.flab.order.domain.event.OrderPayedEvent;
import com.flab.order.domain.exception.AlreadyCanceledException;
import com.flab.order.domain.exception.AlreadyCompletedException;
import com.flab.order.domain.exception.AlreadyPayedException;
import com.flab.order.domain.exception.AmountNotMatchedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends AbstractAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String payMethod;
    private Integer totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime orderedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();


    public enum OrderStatus {
        ORDER_CREATED("주문 생성"),
        ORDER_CANCELED("주문 취소"),
        ORDER_PAYED("주문 결제"),

        ORDER_COMPLETE("주문 완료");

        private final String description;

        OrderStatus(String description) {
            this.description = description;
        }
    }

    protected Order() {
    }

    public Order(
        Long userId,
        String payMethod,
        OrderStatus orderStatus,
        List<OrderLineItem> orderLineItems
    ) {
        this.userId = userId;
        this.payMethod = payMethod;
        this.orderStatus = orderStatus;
        this.orderedAt = LocalDateTime.now();
        this.orderLineItems = orderLineItems;
        this.totalAmount = calculateTotalAmount();
        registerEvent(new OrderCreatedEvent(this));
    }

    public void payed(Integer payedAmount) {
        validPayedAmount(payedAmount);
        validOrderCanPayed();
        this.orderStatus = OrderStatus.ORDER_PAYED;
        registerEvent(new OrderPayedEvent(this));
    }

    private void validPayedAmount(Integer payedAmount) {
        if (!this.totalAmount.equals(payedAmount)) {
            throw new AmountNotMatchedException("결제 금액과 주문 금액이 일치하지 않습니다.");
        }
    }

    private void validOrderCanPayed() {
        if (this.orderStatus == OrderStatus.ORDER_PAYED) {
            throw new AlreadyPayedException("이미 결제된 주문입니다..");
        }
        if (this.orderStatus == OrderStatus.ORDER_CANCELED) {
            throw new AlreadyCanceledException("이미 취소된 주문입니다.");
        }
        if (this.orderStatus == OrderStatus.ORDER_COMPLETE) {
            throw new AlreadyCompletedException("이미 완료된 주문입니다.");
        }
    }

    public Integer calculateTotalAmount() {
        return orderLineItems.stream()
            .mapToInt(OrderLineItem::calculateTotalAmount)
            .sum();
    }

    public static Order create(
        Long userId,
        String payMethod,
        List<OrderLineItem> orderLineItems
    ) {
        return new Order(userId, payMethod, OrderStatus.ORDER_CREATED, orderLineItems);
    }

    public void cancel() {
        this.orderStatus = OrderStatus.ORDER_CANCELED;
        registerEvent(new OrderCanceledEvent(this));
    }

    public void complete() {
        this.orderStatus = OrderStatus.ORDER_COMPLETE;
        registerEvent(new OrderCompletedEvent(this));
    }

    public Long getId() {
        return id;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    public List<Long> getItemIds() {
        return this.orderLineItems.stream()
            .map(OrderLineItem::getItemId)
            .collect(Collectors.toList());
    }

    public List<PayedItemInfo> getPayedItemInfo() {
        return this.orderLineItems.stream().map(
            orderLineItem -> {
                var payedItemInfo = new PayedItemInfo(
                    orderLineItem.getItemId(),
                    orderLineItem.getOrderCount()
                );
                return payedItemInfo;
            }
        ).collect(Collectors.toList());
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
