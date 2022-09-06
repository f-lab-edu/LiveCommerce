package com.flab.livecommerce.domain.product;

import lombok.Getter;

@Getter
public class Product {

    public enum ProductState {
        LIVE_ON, LIVE_OFF
    }

    private Long id;
    private String name;
    //상품 판매자
    private String sellerName;
    //상품 상세
    private String detail;
    //상품 가격
    private int price;
    //상품 개수
    private int stockQuantity;
    //좋아요
    private Long like;
    //라이브 여부
    private ProductState state;
    //private Category category; - 대분류, 중분류
    //private Option option; - 사이즈, 색상, 추가상품 등등
    //private Review review; - 평점 포함
    //private Image image; - 상품 이미지
}