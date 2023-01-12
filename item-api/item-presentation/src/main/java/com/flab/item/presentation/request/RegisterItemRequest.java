package com.flab.item.presentation.request;

import com.flab.item.application.command.RegisterItemCommand;
import com.flab.item.application.command.RegisterItemOptionCommand;
import com.flab.item.application.command.RegisterItemOptionGroupCommand;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class RegisterItemRequest {

    @NotBlank(message = "상품명을 작성하세요.")
    private String name;

    @NotNull(message = "상품 원가를 작성하세요.")
    private Integer price;

    @NotNull(message = "상품 판매가를 작성하세요.")
    @Range(min = 100, max = 200000000, message = "상품 가격은 100원 이상 ~ 2억 미만이어야 합니다.")
    private Integer salesPrice;

    @NotBlank(message = "상품 설명을 작성하세요.")
    private String description;

    @Valid
    private List<RegisterItemOptionGroupRequest> itemOptionGroups;

    private RegisterItemRequest() {
    }

    public RegisterItemCommand toCommand() {
        return new RegisterItemCommand(
            name,
            price,
            salesPrice,
            description,
            toOptionGroup()
        );
    }

    private List<RegisterItemOptionGroupCommand> toOptionGroup() {
        return this.itemOptionGroups.stream().map(
            optionGroupRequest -> new RegisterItemOptionGroupCommand(
                optionGroupRequest.getOrdering(),
                optionGroupRequest.getName(),
                toOption(optionGroupRequest)
            )
        ).collect(Collectors.toList());
    }

    private List<RegisterItemOptionCommand> toOption(
        RegisterItemOptionGroupRequest request
    ) {
        return request.getItemOptions().stream().map(
            optionRequest -> new RegisterItemOptionCommand(
                optionRequest.getName(),
                optionRequest.getOrdering(),
                optionRequest.getPrice()
            )
        ).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSalesPrice() {
        return salesPrice;
    }

    public String getDescription() {
        return description;
    }

    public List<RegisterItemOptionGroupRequest> getItemOptionGroups() {
        return itemOptionGroups;
    }
}
