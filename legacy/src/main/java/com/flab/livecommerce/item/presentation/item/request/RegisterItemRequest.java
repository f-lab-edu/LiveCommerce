package com.flab.livecommerce.item.presentation.item.request;

import com.flab.livecommerce.item.application.command.RegisterItemCommand;
import com.flab.livecommerce.item.application.command.RegisterItemOptionGroupCommand;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterItemRequest {

    private Long sellerId;

    @NotBlank(message = "상품명을 작성하세요.")
    private String name;

    @NotNull(message = "상품 원가를 작성하세요.")
    private Integer price;

    @NotNull(message = "상품 판매가를 작성하세요.")
    @Range(min = 100, message = "상품 가격은 100원 이상이어야 합니다.")
    private Integer salesPrice;

    @NotBlank(message = "상품 설명을 작성하세요.")
    private String description;

    @NotNull(message = "상품 재고를 작성하세요.")
    @Range(min = 1, message = "재고 수량은 1개 이상이어야 합니다.")
    private Integer stockQuantity;

    private List<RegisterItemOptionGroupCommand> itemOptionGroups;

    public RegisterItemCommand toCommand() {
        return new RegisterItemCommand(
            sellerId,
            name,
            price,
            salesPrice,
            description,
            stockQuantity,
            itemOptionGroups
        );
    }
}
