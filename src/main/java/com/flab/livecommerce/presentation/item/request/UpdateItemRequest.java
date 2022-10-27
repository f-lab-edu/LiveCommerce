package com.flab.livecommerce.presentation.item.request;

import com.flab.livecommerce.application.item.command.UpdateItemCommand;
import com.flab.livecommerce.application.item.command.UpdateItemOptionGroupCommand;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
@AllArgsConstructor
public class UpdateItemRequest {
    private Long shopId;

    @NotBlank(message = "상품명을 작성하세요.")
    private String name;

    @NotNull(message = "상품 원가를 작성하세요.")
    private Long price;

    @NotNull(message = "상품 판매가를 작성하세요.")
    @Range(min = 100, message = "상품 가격은 100원 이상이어야 합니다.")
    private Long salesPrice;

    @NotBlank(message = "상품 설명을 작성하세요.")
    private String description;

    @NotNull(message = "상품 재고를 작성하세요.")
    @Range(min = 1, message = "재고 수량은 1개 이상이어야 합니다.")
    private Integer stockQuantity;

    private List<UpdateItemOptionGroupCommand> itemOptionGroups;

    public UpdateItemCommand toCommand() {
        return new UpdateItemCommand(
            shopId,
            name,
            price,
            salesPrice,
            description,
            stockQuantity,
            itemOptionGroups
        );
    }
}
