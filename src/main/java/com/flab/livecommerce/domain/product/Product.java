package com.flab.livecommerce.domain.product;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

// TODO 타이틀이미지, 상세 이미지 리스트 추가
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

    // 모델 번호
    private int modelNumber;

    //옵션 그룹
    private List<OptionGroup> optionGroups = new ArrayList<>();

    @Builder
    public Product(
        String name, String description, int price, int salesPrice,
        int stockQuantity, int modelNumber, List<OptionGroup> optionGroups
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.salesPrice = salesPrice;
        this.stockQuantity = stockQuantity;
        this.modelNumber = modelNumber;
        this.optionGroups.addAll(optionGroups);
    }
}