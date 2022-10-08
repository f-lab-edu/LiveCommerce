package com.flab.livecommerce.presentation.order.request;

import com.flab.livecommerce.application.order.command.RegisterOrderItemOptionCommand;
import com.flab.livecommerce.application.order.command.RegisterOrderItemOptionGroupCommand;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrderItemOptionGroupRequest {

    @NotNull(message = "ordering 를 작성하세요.")
    private Integer ordering;

    @NotBlank(message = "name 를 작성하세요.")
    private String name;

    private List<RegisterOrderItemOptionRequest> orderItemOptions;

    public RegisterOrderItemOptionGroupCommand toCommand() {
        return RegisterOrderItemOptionGroupCommand.builder()
            .ordering(this.ordering)
            .name(this.name)
            .orderItemOptions(toItemOptionCommand())
            .build();
    }

    public List<RegisterOrderItemOptionCommand> toItemOptionCommand() {
        return this.orderItemOptions.stream().map(
            itemOptionRequest -> RegisterOrderItemOptionCommand.builder()
                .ordering(itemOptionRequest.getOrdering())
                .name(itemOptionRequest.getName())
                .price(itemOptionRequest.getPrice())
                .build()
        ).collect(Collectors.toList());
    }
}
