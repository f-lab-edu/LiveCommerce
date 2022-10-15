package com.flab.livecommerce.presentation.item.request;

import com.flab.livecommerce.application.item.command.ItemFormCommand;
import com.flab.livecommerce.application.item.command.ItemOptionGroupFormCommand;
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
public class ItemFormRequest {

    private Long shopId;

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

    private List<ItemOptionGroupFormCommand> itemOptionGroups;

    public ItemFormCommand toCommand() {
        return new ItemFormCommand(
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
