package com.flab.livecommerce.domain.order;

import com.flab.livecommerce.common.exception.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private Long id;
    private Long userId;
    private String payMethod;

    //배송 정보
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverZipcode;
    private String receiverAddress;
    private String receiverDetailAddress;
    private String receiverMessage;

    //주문 생성시간
    private LocalDateTime orderedAt;
    private OrderStatus orderStatus;

    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    public enum OrderStatus {
        ORDER_CREATED("주문 생성"),
        ORDER_COMPLETE("주문 완료");
        private final String description;

        OrderStatus(String description) {
            this.description = description;
        }
    }

    @Builder
    public Order(
        Long userId,
        String payMethod,
        String receiverName,
        String receiverPhoneNumber,
        String receiverZipcode,
        String receiverAddress,
        String receiverDetailAddress,
        String receiverMessage,
        List<OrderLineItem> orderLineItems
    ) {
        if (receiverName == null && receiverName.length() == 0) {
            throw new InvalidParameterException("Order.receiverName");
        }
        if (receiverPhoneNumber == null && receiverPhoneNumber.length() == 0) {
            throw new InvalidParameterException("Order.payMethod");
        }
        if (receiverZipcode == null && receiverZipcode.length() == 0) {
            throw new InvalidParameterException("Order.payMethod");
        }
        if (receiverAddress == null && receiverAddress.length() == 0) {
            throw new InvalidParameterException("Order.payMethod");
        }
        if (receiverDetailAddress == null && receiverDetailAddress.length() == 0) {
            throw new InvalidParameterException("Order.payMethod");
        }
        if (receiverMessage == null && receiverMessage.length() == 0) {
            throw new InvalidParameterException("Order.payMethod");
        }

        this.userId = userId;
        this.payMethod = payMethod;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.receiverZipcode = receiverZipcode;
        this.receiverAddress = receiverAddress;
        this.receiverDetailAddress = receiverDetailAddress;
        this.receiverMessage = receiverMessage;
        this.orderedAt = LocalDateTime.now();
        this.orderStatus = OrderStatus.ORDER_CREATED;
        this.orderLineItems = orderLineItems;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }
}
