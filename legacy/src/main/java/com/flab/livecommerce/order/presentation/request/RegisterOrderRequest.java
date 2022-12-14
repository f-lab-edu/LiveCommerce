package com.flab.livecommerce.order.presentation.request;

import com.flab.livecommerce.order.application.command.RegisterOrderCommand;
import com.flab.livecommerce.order.application.command.RegisterOrderLineItemCommand;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrderRequest {

    @NotNull(message = "userId 를 작성하세요.")
    private Long userId;

    @NotBlank(message = "payMethod 를 작성하세요.")
    private String payMethod;

    @NotBlank(message = "receiverName 를 작성하세요.")
    private String receiverName;

    @NotBlank(message = "receiverPhoneNumber 를 작성하세요.")
    private String receiverPhoneNumber;

    @NotBlank(message = "receiverZipcode 를 작성하세요.")
    private String receiverZipcode;

    @NotBlank(message = "receiverAddress 를 작성하세요.")
    private String receiverAddress;

    @NotBlank(message = "receiverDetailAddress 를 작성하세요.")
    private String receiverDetailAddress;

    @NotBlank(message = "receiverMessage 를 작성하세요.")
    private String receiverMessage;

    @Valid
    private List<RegisterOrderLineItemRequest> orderLineItems;

    public RegisterOrderCommand toCommand() {
        return new RegisterOrderCommand(
            this.userId,
            this.payMethod,
            this.receiverName,
            this.receiverPhoneNumber,
            this.receiverZipcode,
            this.receiverAddress,
            this.receiverDetailAddress,
            this.receiverMessage,
            toLineItemCommand()
        );
    }

    public List<RegisterOrderLineItemCommand> toLineItemCommand() {
        return this.orderLineItems.stream().map(
            lineItemRequest -> RegisterOrderLineItemCommand.builder()
                .itemId(lineItemRequest.getItemId())
                .orderCount(lineItemRequest.getOrderCount())
                .name(lineItemRequest.getName())
                .price(lineItemRequest.getPrice())
                .orderItemOptionGroups(lineItemRequest.toItemOptionGroupCommand())
                .build()
        ).collect(Collectors.toList());
    }
}
