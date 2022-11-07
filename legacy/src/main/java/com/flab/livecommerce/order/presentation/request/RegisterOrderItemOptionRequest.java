package com.flab.livecommerce.order.presentation.request;

import com.flab.livecommerce.order.application.command.RegisterOrderItemOptionCommand;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterOrderItemOptionRequest {

    @NotNull(message = "ordering 을 작성하세요.")
    private Integer ordering;

    @NotBlank(message = "name 을 작성하세요.")
    private String name;

    @NotNull(message = "price 를 작성하세요.")
    private Long price;

    public RegisterOrderItemOptionCommand toCommand() {
        return RegisterOrderItemOptionCommand.builder()
            .ordering(this.ordering)
            .name(this.name)
            .price(this.price)
            .build();
    }
}
