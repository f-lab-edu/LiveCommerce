package com.flab.livecommerce.application.order.command;

import com.flab.livecommerce.domain.order.Order;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterOrderCommand {

    private Long userId;
    private String payMethod;
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverZipcode;
    private String receiverAddress;
    private String receiverDetailAddress;
    private String receiverMessage;

    private List<RegisterOrderLineItemCommand> orderLineItems;

    public Order toEntity() {
        return Order.builder()
            .userId(this.userId)
            .payMethod(this.payMethod)
            .receiverName(this.receiverName)
            .receiverPhoneNumber(this.receiverPhoneNumber)
            .receiverZipcode(this.receiverZipcode)
            .receiverAddress(this.receiverAddress)
            .receiverDetailAddress(this.receiverDetailAddress)
            .receiverMessage(this.receiverMessage)
            .build();
    }
}
