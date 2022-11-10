package com.flab.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class Order {

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
        ORDER_PAID("주문 결제");
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
    }

    public void paid() {
        if (this.orderStatus == OrderStatus.ORDER_CREATED) {
            this.orderStatus = OrderStatus.ORDER_PAID;
            return;
        }
        throw new IllegalStateException();
    }

    public Integer calculateTotalAmount() {
        var totalAmount = orderLineItems.stream()
            .mapToInt(OrderLineItem::calculateTotalAmount)
            .sum();

        return totalAmount;
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
    }

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return totalAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
