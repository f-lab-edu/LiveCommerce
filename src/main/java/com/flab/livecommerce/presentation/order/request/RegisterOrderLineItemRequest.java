package com.flab.livecommerce.presentation.order.request;

import com.flab.livecommerce.application.order.command.RegisterOrderItemOptionGroupCommand;
import com.flab.livecommerce.application.order.command.RegisterOrderLineItemCommand;
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
public class RegisterOrderLineItemRequest {

    @NotNull(message = "orderCount 를 작성하세요.")
    private Integer orderCount;

    @NotNull(message = "itemId 를 작성하세요.")
    private Long itemId;

    @NotBlank(message = "name 을 작성하세요.")
    private String name;

    @NotNull(message = "price 를 작성하세요.")
    private Long price;

    @Valid
    private List<RegisterOrderItemOptionGroupRequest> orderItemOptionGroups;

    public List<RegisterOrderItemOptionGroupCommand> toItemOptionGroupCommand() {
        return this.orderItemOptionGroups.stream().map(
            itemOptionGroupRequest -> RegisterOrderItemOptionGroupCommand.builder()
                .ordering(itemOptionGroupRequest.getOrdering())
                .name(itemOptionGroupRequest.getName())
                .orderItemOptions(itemOptionGroupRequest.toItemOptionCommand())
                .build()
        ).collect(Collectors.toList());
    }

}
