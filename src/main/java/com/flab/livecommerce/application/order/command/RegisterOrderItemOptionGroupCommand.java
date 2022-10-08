package com.flab.livecommerce.application.order.command;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterOrderItemOptionGroupCommand {

    private Integer ordering;
    private String name;
    private List<RegisterOrderItemOptionCommand> orderItemOptions;

}
