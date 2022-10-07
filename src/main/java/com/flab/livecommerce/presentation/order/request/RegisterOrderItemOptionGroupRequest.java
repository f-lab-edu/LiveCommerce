package com.flab.livecommerce.presentation.order.request;

import java.util.List;
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
}
