package com.flab.livecommerce.domain.product;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Product {

    private Long id;
    private String name;
    //상품 설명
    private String description;
    //상품 원가
    private int price;
    //상품 판매가
    private int salesPrice;
    //상품 재고
    private int stockQuantity;
    //옵션 그룹
    private List<OptionGroup> optionGroups = new ArrayList<>();

}