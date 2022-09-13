package com.flab.livecommerce.presentation.product.request;

import com.flab.livecommerce.domain.product.OptionGroup;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "상품명을 작성하세요.")
    private String name;

    @NotBlank(message = "상품 설명을 작성하세요.")
    private String description;

    @NotBlank(message = "상품 원가를 작성하세요.")
    private int price;

    @NotBlank(message = "상품 판매가를 작성하세요.")
    @Range(min = 100, message = "상품 가격은 100원 이상이어야 합니다.")
    private int salesPrice;

    @NotBlank(message = "상품 재고를 작성하세요.")
    @Range(min = 1, message = "재고 수량은 1개 이상이어야 합니다.")
    private int stockQuantity;

    @NotBlank(message = "상품 모델명을 작성하세요.")
    private int modelNumber;

    private List<OptionGroup> optionGroups = new ArrayList<>();
}
